interface CommandCentre {
    void notify(Aircraft aircraft, String event);
    void registerRunway(Runway runway);
}

class ConcreteCommandCentre implements CommandCentre {
    private Runway runway;

    public void registerRunway(Runway runway) {
        this.runway = runway;
    }

    public void notify(Aircraft aircraft, String event) {
        if (event.equals("takeoff")) {
            if (!runway.isOccupied()) {
                runway.setOccupied(true);
                System.out.println(aircraft.getName() + " took off.");
            } else {
                System.out.println(aircraft.getName() + " must wait. Runway is occupied.");
            }
        } else if (event.equals("land")) {
            runway.setOccupied(false);
            System.out.println(aircraft.getName() + " has landed.");
        }
    }
}

class Aircraft {
    private String name;
    private CommandCentre commandCentre;

    public Aircraft(String name, CommandCentre commandCentre) {
        this.name = name;
        this.commandCentre = commandCentre;
    }

    public String getName() {
        return name;
    }

    public void requestTakeoff() {
        commandCentre.notify(this, "takeoff");
    }

    public void land() {
        commandCentre.notify(this, "land");
    }
}

class Runway {
    private boolean occupied = false;

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}

public class MediatorPattern {
    public static void run() {
        ConcreteCommandCentre centre = new ConcreteCommandCentre();
        Runway runway = new Runway();
        centre.registerRunway(runway);

        Aircraft plane1 = new Aircraft("Boeing-737", centre);
        Aircraft plane2 = new Aircraft("Airbus-A320", centre);

        plane1.requestTakeoff();
        plane2.requestTakeoff();
        plane1.land();
        plane2.requestTakeoff();
    }
}

