import java.util.Arrays;
import java.util.List;

public class Element {
    private String nama;
    private List<Element> weakness;
    private List<Element> evolutionOptions;

    // Definisi elemen-elemen
    public static final Element API = new Element("API");
    public static final Element ANGIN = new Element("ANGIN");
    public static final Element AIR = new Element("AIR");
    public static final Element ES = new Element("ES");
    public static final Element TANAH = new Element("TANAH");

    static {
        // Mengatur kelemahan setiap elemen
        API.weakness = Arrays.asList(AIR, TANAH);
        ANGIN.weakness = Arrays.asList(ES, API);
        AIR.weakness = Arrays.asList(ES, TANAH);
        ES.weakness = Arrays.asList(API, TANAH);
        TANAH.weakness = Arrays.asList(AIR, ANGIN);

        // Mengatur pilihan evolusi setiap elemen
        API.evolutionOptions = Arrays.asList(ANGIN, TANAH);
        ANGIN.evolutionOptions = Arrays.asList(API, AIR);
        AIR.evolutionOptions = Arrays.asList(ES, ANGIN);
        ES.evolutionOptions = Arrays.asList(AIR, TANAH);
        TANAH.evolutionOptions = Arrays.asList(API, ES);
    }

    Element(String nama) {
        this.nama = nama;
    }

    public boolean isEffectiveAgainst(Element element) {
        return weakness.contains(element);
    }

    public List<Element> getWeakness() {
        return weakness;
    }

    public String getNama() {
        return nama;
    }

    public List<Element> getEvolutionOptions() {
        return evolutionOptions;
    }
}
