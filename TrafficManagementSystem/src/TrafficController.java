import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficController {

    private static TrafficController instance;
    private HashMap<String, Road> roads;
    private HashMap<String, Boolean> emergencyStatus;
    private ReentrantLock emergencyLock;

    private TrafficController() {
        roads = new HashMap<>();
        emergencyStatus = new HashMap<>();
        emergencyLock = new ReentrantLock();
    }


    public static TrafficController getInstance(){
        if(instance == null){
            instance = new TrafficController();
        }
        return instance;
    }

    public void addRoads(Road road){
        roads.put(road.getId(),road);
    }

    public void removeRoads(String roadId){
        roads.remove(roadId);
    }


    public void startTrafficControl(){
        for(Road road : roads.values()){
            TrafficLight light = road.getTrafficLight();
            new Thread(() -> {
                while(true){
                    try{
                        Thread.sleep(light.getRedDuration());
                        light.changeSignal(Signal.GREEN);
                        Thread.sleep(light.getGreenDuration());
                        light.changeSignal(Signal.YELLOW);
                        Thread.sleep(light.getYellowDuration());
                        light.changeSignal(Signal.RED);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void handleEmergency(String roadId){
        emergencyLock.lock();
        try{
            Road emergencyRoad = roads.get(roadId);
            TrafficLight emergencyLight = emergencyRoad.getTrafficLight();
            emergencyStatus.put(roadId,true);
            emergencyLight.changeSignal(Signal.GREEN);

            for(Road road: roads.values()){
                if(!road.getId().equals(roadId)) {
                    TrafficLight light = road.getTrafficLight();
                    light.changeSignal(Signal.RED);
                }
            }

            new Thread(() -> {
                try{
                    Thread.sleep(180000);
                    emergencyLock.lock();
                    try{
                        emergencyStatus.put(roadId,false);
                        System.out.println("Emergency situation resolved for road: " + roadId);
                        resetTrafficFlow();
                    }finally {
                        emergencyLock.unlock();
                    }
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }, "EmergencyHandler-" + roadId).start();

            System.out.println("Emergency handling activated for road: " + roadId);
        }finally {
            emergencyLock.unlock();
        }
    }


    public void resetTrafficFlow(){
        boolean ifAny = emergencyStatus.values().stream().anyMatch(status -> status);

        if(!ifAny){
            for(Road road : roads.values()){
                TrafficLight light = road.getTrafficLight();
                light.changeSignal(Signal.RED);
            }
            startTrafficControl();
        }
    }

    public boolean isAnEmergencyState(String roadId){
        return emergencyStatus.getOrDefault(roadId, false);
    }

    public void cancelEmergency(String roadId){
        emergencyLock.lock();
        try{
            emergencyStatus.put(roadId, false);
            resetTrafficFlow();
            System.out.println("Emergency state cancelled for road: " + roadId);
        }finally {
            emergencyLock.unlock();
        }
    }
}
