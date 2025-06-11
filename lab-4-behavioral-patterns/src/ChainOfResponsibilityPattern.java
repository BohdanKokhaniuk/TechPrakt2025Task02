import java.util.Scanner;

abstract class SupportHandler {
    protected SupportHandler next;

    public void setNext(SupportHandler next) {
        this.next = next;
    }

    public abstract boolean handle(String issue);
}

class Level1Support extends SupportHandler {
    public boolean handle(String issue) {
        if (issue.equalsIgnoreCase("1")) {
            System.out.println("Level 1: Password reset handled.");
            return true;
        }
        if (next != null) return next.handle(issue);
        return false;
    }
}

class Level2Support extends SupportHandler {
    public boolean handle(String issue) {
        if (issue.equalsIgnoreCase("2")) {
            System.out.println("Level 2: Software issue handled.");
            return true;
        }
        if (next != null) return next.handle(issue);
        return false;
    }
}

class Level3Support extends SupportHandler {
    public boolean handle(String issue) {
        if (issue.equalsIgnoreCase("3")) {
            System.out.println("Level 3: Hardware issue handled.");
            return true;
        }
        if (next != null) return next.handle(issue);
        return false;
    }
}

class FinalLevelSupport extends SupportHandler {
    public boolean handle(String issue) {
        System.out.println("Final Level: Escalating to human agent.");
        return true;
    }
}

public class ChainOfResponsibilityPattern {
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler level3 = new Level3Support();
        SupportHandler finalLevel = new FinalLevelSupport();

        level1.setNext(level2);
        level2.setNext(level3);
        level3.setNext(finalLevel);

        boolean resolved = false;
        while (!resolved) {
            System.out.println("Select support issue:");
            System.out.println("1. Password Reset\n2. Software Issue\n3. Hardware Issue\nOther. Speak to Agent");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();
            resolved = level1.handle(choice);
        }
    }
}

