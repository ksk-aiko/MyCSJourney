package DesignPattern.ProtoTypePattern;

import java.util.Map;
import java.util.HashMap;

interface ProtoType<E> {
    abstract E clone();
}

interface Stamp extends ProtoType<Stamp> {
    abstract String getTitle();
    abstract String getRender();
    abstract String getColor();
    abstract Stamp clone();
}

class GenericStamp implements Stamp {
    private final static String TITLE = "Generic Stamp";
    private final static String RENDER = "❓";

    protected String title;
    protected String render;
    protected String color;

    public GenericStamp(){};

    public GenericStamp(String color) {
        this.title = GenericStamp.TITLE;
        this.render = GenericStamp.RENDER;
        this.color = color;
    }

    public GenericStamp(Stamp stamp) {
        this.title = GenericStamp.TITLE;
        this.render = GenericStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new GenericStamp(this);
    }
}

class StarStamp extends GenericStamp {
    private final static String TITLE = "Star";
    private final static String RENDER = "⭐️";

    public StarStamp(String color) {
        this.title = StarStamp.TITLE;
        this.render = StarStamp.RENDER;
        this.color = color;
    }

    public StarStamp(Stamp stamp) {
        this.title = StarStamp.TITLE;
        this.render = StarStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone() {
        return new StarStamp(this.getColor());
    }
}

class HeartStamp extends GenericStamp {
    private final static String TITLE = "Heart";
    private final static String RENDER = "❤️";

    public HeartStamp(String color) {
        this.title = HeartStamp.TITLE;
        this.render = HeartStamp.RENDER;
        this.color = color;
    }

    public HeartStamp(Stamp stamp) {
        this.title = HeartStamp.TITLE;
        this.render = HeartStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone() {
        return new HeartStamp(this);
    }
}

class PeaceStamp extends GenericStamp {
    private final static String TITLE = "Peace";
    private final static String RENDER = "✌️";

    public PeaceStamp(){};

    public PeaceStamp(String color) {
        this.title = PeaceStamp.TITLE;
        this.render = PeaceStamp.RENDER;
        this.color = color;
    }

    public PeaceStamp(Stamp stamp) {
        this.title = PeaceStamp.TITLE;
        this.render = PeaceStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone() {
        return new PeaceStamp(this);
    }
}

class TreeStamp extends GenericStamp {
    private final static String TITLE = "Tree";
    private final static String RENDER = "🌲";

    public TreeStamp(String color) {
        this.title = TreeStamp.TITLE;
        this.render = TreeStamp.RENDER;
        this.color = color;
    }

    public TreeStamp(Stamp stamp) {
        this.title = TreeStamp.TITLE;
        this.render = TreeStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone() {
        return new TreeStamp(this);
    }
}

class RainbowStamp extends GenericStamp {
    private final static String TITLE = "Rainbow";
    private final static String RENDER = "🌈";

    public RainbowStamp(String color) {
        this.title = RainbowStamp.TITLE;
        this.render = RainbowStamp.RENDER;
        this.color = color;
    }

    public RainbowStamp(Stamp stamp) {
        this.title = RainbowStamp.TITLE;
        this.render = RainbowStamp.RENDER;
        this.color = stamp.getColor();
    }

    public Stamp clone() {
        return new RainbowStamp(this);
    }
}

class BearStamp extends GenericStamp {
    private final static String TITLE = "Bear";
    private final static String RENDER = "🐻";


    public BearStamp(){};

    public BearStamp(String color) {
        this.title = BearStamp.TITLE;
        this.render = BearStamp.RENDER;
        this.color = color;
    }

    public BearStamp(Stamp stamp) {
        this.title = BearStamp.TITLE;
        this.render = BearStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new BearStamp(this);
    }
}

class AppleStamp extends GenericStamp {
    private final static String TITLE = "Apple";
    private final static String RENDER = "🍎";

    public AppleStamp(){};

    public AppleStamp(String color) {
        this.title = AppleStamp.TITLE;
        this.render = AppleStamp.RENDER;
        this.color = color;
    }

    public AppleStamp(Stamp stamp) {
        this.title = AppleStamp.TITLE;
        this.render = AppleStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new AppleStamp(this);
    }
}

class ThunderStamp extends GenericStamp {
    private final static String TITLE = "Thunder";
    private final static String RENDER = "⚡️";

    public ThunderStamp(){};

    public ThunderStamp(String color) {
        this.title = ThunderStamp.TITLE;
        this.render = ThunderStamp.RENDER;
        this.color = color;
    }

    public ThunderStamp(Stamp stamp) {
        this.title = ThunderStamp.TITLE;
        this.render = ThunderStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new ThunderStamp(this);
    }
}

class MusicNoteStamp extends GenericStamp {
    private final static String TITLE = "Music Note";
    private final static String RENDER = "🎵";

    public MusicNoteStamp(){};

    public MusicNoteStamp(String color) {
        this.title = MusicNoteStamp.TITLE;
        this.render = MusicNoteStamp.RENDER;
        this.color = color;
    }

    public MusicNoteStamp(Stamp stamp) {
        this.title = MusicNoteStamp.TITLE;
        this.render = MusicNoteStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new MusicNoteStamp(this);
    }
}

class RingStamp extends GenericStamp {
    private final static String TITLE = "Ring";
    private final static String RENDER = "💍";

    public RingStamp(){};

    public RingStamp(String color) {
        this.title = RingStamp.TITLE;
        this.render = RingStamp.RENDER;
        this.color = color;
    }

    public RingStamp(Stamp stamp) {
        this.title = RingStamp.TITLE;
        this.render = RingStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new RingStamp(this);
    }
}

class HorseStamp extends GenericStamp {
    private final static String TITLE = "Horse";
    private final static String RENDER = "🐴";

    public HorseStamp(){};

    public HorseStamp(String color) {
        this.title = HorseStamp.TITLE;
        this.render = HorseStamp.RENDER;
        this.color = color;
    }

    public HorseStamp(Stamp stamp) {
        this.title = HorseStamp.TITLE;
        this.render = HorseStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new HorseStamp(this);
    }
}

class CloudStamp extends GenericStamp {
    private final static String TITLE = "Cloud";
    private final static String RENDER = "☁️";

    public CloudStamp(){};

    public CloudStamp(String color) {
        this.title = CloudStamp.TITLE;
        this.render = CloudStamp.RENDER;
        this.color = color;
    }

    public CloudStamp(Stamp stamp) {
        this.title = CloudStamp.TITLE;
        this.render = CloudStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new CloudStamp(this);
    }
}

class SkullStamp extends GenericStamp {
    private final static String TITLE = "Skull";
    private final static String RENDER = "💀";

    public SkullStamp(){};

    public SkullStamp(String color) {
        this.title = SkullStamp.TITLE;
        this.render = SkullStamp.RENDER;
        this.color = color;
    }

    public SkullStamp(Stamp stamp) {
        this.title = SkullStamp.TITLE;
        this.render = SkullStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new SkullStamp(this);
    }
}

class VampireTeethStamp extends GenericStamp {
    private final static String TITLE = "Vampire Teeth";
    private final static String RENDER = "🧛";

    public VampireTeethStamp(){};

    public VampireTeethStamp(String color) {
        this.title = VampireTeethStamp.TITLE;
        this.render = VampireTeethStamp.RENDER;
        this.color = color;
    }

    public VampireTeethStamp(Stamp stamp) {
        this.title = VampireTeethStamp.TITLE;
        this.render = VampireTeethStamp.RENDER;
        this.color = stamp.getColor();
    }

    public String getTitle() {
        return this.title;
    }

    public String getRender() {
        return this.render;
    }

    public String getColor() {
        return this.color;
    }

    public Stamp clone() {
        return new VampireTeethStamp(this);
    }
}

class StampProtoTypeFactory {
    private static Map<Integer, Stamp> stampRegistry;
    private final static String DEFAULT_COLOR = "#e74c3c";

    static {
        StampProtoTypeFactory.stampRegistry = new HashMap<Integer, Stamp>();
        StampProtoTypeFactory.stampRegistry.put(0, new GenericStamp(StampProtoTypeFactory.DEFAULT_COLOR));
        StampProtoTypeFactory.stampRegistry.put(1, new StarStamp(StampProtoTypeFactory.DEFAULT_COLOR));
        StampProtoTypeFactory.stampRegistry.put(2, new HeartStamp(StampProtoTypeFactory.DEFAULT_COLOR));
        StampProtoTypeFactory.stampRegistry.put(3, new PeaceStamp(StampProtoTypeFactory.DEFAULT_COLOR));
        StampProtoTypeFactory.stampRegistry.put(4, new BearStamp(StampProtoTypeFactory.DEFAULT_COLOR));
        StampProtoTypeFactory.stampRegistry.put(5, new HorseStamp(StampProtoTypeFactory.DEFAULT_COLOR));
        StampProtoTypeFactory.stampRegistry.put(6, new CloudStamp(StampProtoTypeFactory.DEFAULT_COLOR));
        StampProtoTypeFactory.stampRegistry.put(7, new SkullStamp(StampProtoTypeFactory.DEFAULT_COLOR));
        StampProtoTypeFactory.stampRegistry.put(8, new VampireTeethStamp(StampProtoTypeFactory.DEFAULT_COLOR));
    }

    public static void updateProtoType(Integer key, Stamp stamp) {
        StampProtoTypeFactory.stampRegistry.put(key, stamp);
    }

    public static Stamp get(Integer key) {
        Stamp p = StampProtoTypeFactory.stampRegistry.get(key);
        if (p != null) return p.clone();
        return null;
    }
}

class FairyWorld {
    public void photoBoothShoot(Integer[] stampSlots) {
        String endl = System.lineSeparator();

        for (int i = 0; i < stampSlots.length; i++) {
            System.out.println("Stamp - " + StampProtoTypeFactory.get(stampSlots[i]) + " used in the photoshoot!");
        }
    }
}

class Main {
    public static void main(String[] args) {
        String endl = System.lineSeparator();

        System.out.println(StampProtoTypeFactory.get(1));
        System.out.println(StampProtoTypeFactory.get(0));
        System.out.println(StampProtoTypeFactory.get(2));

        StampProtoTypeFactory.updateProtoType(9, new RingStamp("#f1c40f"));
        StampProtoTypeFactory.updateProtoType(10, new MusicNoteStamp("#f1c40f"));
        StampProtoTypeFactory.updateProtoType(11, new ThunderStamp("#f1c40f"));
        StampProtoTypeFactory.updateProtoType(12,  new AppleStamp("#f1c40f"));

        FairyWorld fairyWorld = new FairyWorld();

        fairyWorld.photoBoothShoot(new Integer[]{9, 10, 11, 12});
    }
}