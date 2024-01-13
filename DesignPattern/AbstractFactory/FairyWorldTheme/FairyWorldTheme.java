import java.util.HashMap;
import java.util.ArrayList;

class FairyWorld {
    public void demoTime(FairyWorldThemeFactory factory) {
        System.out.println(factory.introduce());
        Poster poster = factory.createPoster();
        System.out.println("-----This theme park has seasonal signage!-----");
        System.out.println(poster.getPoster());
        System.out.println("-----This theme park has seasonal lights!-----");
        StringOfLights stringOfLights = factory.createStringOfLights();
        System.out.println(stringOfLights.turnOnTheLights());
        System.out.println("-----This theme park has seasonal light shows!-----");
        LightShow lightShow = factory.createLightShow();
        lightShow.narration();
        System.out.println("-----This theme park has seasonal music!-----");
        MusicSong musicSong = factory.createMusicSong();
        musicSong.playMusic();
        System.out.println("-----In this theme park, mascots will happily greet you!!-----");
        Mascot[] mascots = factory.createMascots();
        for (int i = 0; i < mascots.length; i++) {
            mascots[i].introduceMascot();
        }
    }
}

interface FairyWorldThemeFactory {

    public String introduce();
    public Poster createPoster();
    public StringOfLights createStringOfLights();
    public LightShow createLightShow();
    public MusicSong createMusicSong();
    public Mascot[] createMascots();

}

class Person {

    private String firstName;
    private String lastName;
    private int age;
    private String biologicalSex;

    public Person(String firstName, String lastName, int age, String biologicalSex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.biologicalSex = biologicalSex;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public String getBiologicalSex() {
        return this.biologicalSex;
    }
}



class Poster {
    private static final HashMap<String, String> list = new HashMap<String, String>();
    private String season;

    static {
        list.put("spring", "Cherry blossoms are in full bloom and a gentle spring breeze is blowing. People are enjoying cherry blossom viewing in the park.");
        list.put("summer", "The sun is shining brightly and the sky is clear. People are enjoying the summer festival.");
        list.put("autumn", "The sky is clear and the air is crisp. People are enjoying the autumn leaves.");
        list.put("winter", "The snow is falling and the sky is clear. People are enjoying the winter festival.");
        list.put("christmas", "The snow is falling and the sky is clear. People are enjoying the Christmas festival.");
        list.put("valentine", "The sky is clear and the air is crisp. People are enjoying the Valentine's Day festival.");

    }

    public Poster(String season) {
        this.season = season;
    }

    public String getPoster() {
        return list.get(this.season);
    }
}

class StringOfLights {
    private RGB[] RGBLights;

    public StringOfLights(RGB[] RGBLights) {
        this.RGBLights = RGBLights;
    }

    public String turnOnTheLights() {
        String emittingMessage = "";
        for (int i = 0; i < RGBLights.length; i++) {
            emittingMessage += RGBLights[i].getColor() + " ";
        }
        // emittingを3回繰り返す
        return "In this season, light emit " + emittingMessage.repeat(3);
    }
}

class RGB {
    private int red;
    private int green;
    private int blue;

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public String getColor() {
        // RGBの値に応じた色の名前を返す
        if (red > 200 && green < 100 && blue < 100) {
            return "Red";
        } else if (red < 100 && green > 200 && blue < 100) {
            return "Green";
        } else if (red < 100 && green < 100 && blue > 200) {
            return "Blue";
        } else if (red > 200 && green > 200 && blue < 100) {
            return "Yellow";
        } else if (red > 100 && green < 100 && blue > 100) {
            return "Purple";
        } else if (red > 200 && green < 150 && blue > 150) {
            return "Pink";
        } else {
            return "white";
        }
    }
}


class LightShow {
    private static final HashMap<String, String> showThemeList = new HashMap<String, String>();
    private String season;

    public LightShow(String season) {
        this.season = season;
    }

    static {
    showThemeList.put("spring", "Cherry blossoms dance in the night sky, heralding the arrival of spring in a dazzling light show. People bask under the dreamlike sakura trees, enjoying the evening.");
    showThemeList.put("summer", "On a hot summer night, colorful lights heighten the festive buzz. Music and dance fill the air with vibrant energy.");
    showThemeList.put("autumn", "In the long autumn nights, illuminated leaves create a mystical scene among the trees. People gather, lured by the gentle autumn breeze.");
    showThemeList.put("winter", "In the quiet of winter night, sparkling snow begins a dazzling light show. Snowflakes lit up, casting winter's magic spell.");
    showThemeList.put("christmas", "On Christmas night, glittering decorations light up the streets, enhancing the festive spirit. People celebrate the special night amid enchanting lights.");
    showThemeList.put("valentine", "On Valentine's night, a romantic light show unfolds, drawing couples together. Sweet music fills the air, setting the mood.");
}
    public void narration() {
        System.out.println("-----Narration-----");
        System.out.println(showThemeList.get(this.season));
    }
}

class MusicSong {
    private static final HashMap<String, Song> songList = new HashMap<String, Song>();
    private String season;

    public MusicSong(String season) {
        this.season = season;
    }

    static {
        songList.put("spring", new Song("Sakura", "Sakura, Sakura, Yayoi no sora wa, Mi-watasu kagiri, Kasumi ka kumo ka, Nioi zo izuru, Iza ya, Iza ya, Mi ni yukan", 60));
        songList.put("summer", new Song("Summer Festival", "Natsu matsuri, Natsu matsuri, Minna de tanoshimou, Yama mo kawa, mo umi mo, Minna de tanoshimou", 60));
        songList.put("autumn", new Song("Autumn Leaves", "Koyo, koyo, Koyo no kisetsu ga, Kita kara kuru, Kaze ni yurete, Koyo, koyo, Koyo no kisetsu ga, Kita kara kuru, Kaze ni yurete", 60));
        songList.put("winter", new Song("Winter Festival", "Fuyu matsuri, Fuyu matsuri, Minna de tanoshimou, Yama mo kawa, mo umi mo, Minna de tanoshimou", 60));
    }

    public void playMusic() {
        System.out.println("-----Music-----");
        System.out.println(songList.get(this.season).getTitle());
        System.out.println(songList.get(this.season).getLyrics());
    }
}

class Song {
    private String title;
    private String lyrics;
    private int length;

    public Song(String title, String lyrics, int length) {
        this.title = title;
        this.lyrics = lyrics;
        this.length = length;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLyrics() {
        return this.lyrics;
    }

    public int getLength() {
        return this.length;
    }
}

class Mascot {
    private String name;
    private double height;
    private double width;
    private String appearance;
    private Person insidePerson;

    public Mascot(String name, double height, double width, String appearance, Person insidePerson) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.appearance = appearance;
        this.insidePerson = insidePerson;
    }

    public String getName() {
        return this.name;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public String getAppearance() {
        return this.appearance;
    }

    public Person getInsidePerson() {
        return this.insidePerson;
    }

    // 上記のメソッドを使って、マスコットの情報を出力するメソッドを作る
    public void introduceMascot() {
        System.out.println("Mascot " + this.name + " is " + this.height + " meters tall and " + this.width + " meters wide.");
        System.out.println("Mascot " + this.name + " is " + this.appearance + ".");
        System.out.println("Mascot " + this.name + " is " + this.insidePerson.getName() + ".");
        System.out.println("Mascot " + this.name + " is " + this.insidePerson.getAge() + " years old.");
        System.out.println("Mascot " + this.name + " is " + this.insidePerson.getBiologicalSex() + ".");
    }
}

class SpringThemeFactory implements FairyWorldThemeFactory {

    public String introduce() {
        return "--------Spring is the season of cherry blossoms and fresh greenery.--------";
    }

    public Poster createPoster() {
        return new Poster("spring");
    }

    public StringOfLights createStringOfLights() {
        RGB[] RGBLights = new RGB[2];
        // ピンクの光を作る
        RGBLights[0] = new RGB(255, 140, 203);
        // 白の光を作る
        RGBLights[1] = new RGB(255, 255, 255);
        return new StringOfLights(RGBLights);
    }

    public LightShow createLightShow() {
        return new LightShow("spring");
    }

    public MusicSong createMusicSong() {
        return new MusicSong("spring");
    }

    public Mascot[] createMascots() {
        Mascot[] mascots = new Mascot[2];

        // 例えば、桜をテーマにしたマスコット
        mascots[0] = new Mascot("Sakura", 2.0, 1.0, "Pink with cherry blossoms", new Person("Alice", "Blossom", 25,"female"));

        // 春の花をテーマにしたマスコット
        mascots[1] = new Mascot("Flora", 1.8, 1.2, "Colorful with spring flowers", new Person("Bob", "Green", 30,"male"));

        return mascots;
    }

}



class Main {
    public static void main(String[] args) {
        FairyWorld fairyWorld = new FairyWorld();
        fairyWorld.demoTime(new SpringThemeFactory());
    }
}