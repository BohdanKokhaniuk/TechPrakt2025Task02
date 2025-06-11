interface Hero {
    void attack();
}

class Warrior implements Hero {
    public void attack() {
        System.out.println("Warrior attacks with sword!");
    }
}

class Mage implements Hero {
    public void attack() {
        System.out.println("Mage casts a fireball!");
    }
}

class HeroDecorator implements Hero {
    protected Hero baseHero;

    public HeroDecorator(Hero hero) {
        this.baseHero = hero;
    }

    public void attack() {
        baseHero.attack();
    }
}

class Armor extends HeroDecorator {
    public Armor(Hero hero) {
        super(hero);
    }

    public void attack() {
        baseHero.attack();
        System.out.println(" + wearing armor");
    }
}

class MagicRing extends HeroDecorator {
    public MagicRing(Hero hero) {
        super(hero);
    }

    public void attack() {
        baseHero.attack();
        System.out.println(" + empowered by magic ring");
    }
}

class DecoratorPattern {
    public static void run() {
        Hero mage = new MagicRing(new Armor(new Mage()));
        mage.attack();

        Hero warrior = new Armor(new Armor(new Warrior()));
        warrior.attack();
    }
}

