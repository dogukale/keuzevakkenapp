package com.example.keuzevakkenapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.keuzevakkenapp.KeuzevakAdapter;
import com.example.keuzevakkenapp.R;
import com.example.keuzevakkenapp.database.Keuzevak;

import org.jetbrains.annotations.NotNull;

public class KeuzevakFragment extends Fragment {

    private Keuzevak[] keuzevak;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private GridLayoutManager gridLayoutManager;
    private KeuzevakAdapter keuzevakAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_keuzevak, container, false);

        // Add entries in Database
        final Keuzevak[] keuzevak = new Keuzevak[29];
        keuzevak[0] = new Keuzevak("ITRAPP", "Taal in Rapporteren", "Student weet hoe hij schrijftaken moet aanpakken. Student onderscheidt begrippen, doelgroep, kader van tekst, probleemstelling, enz. en kan deze toepassen. Student verbetert verder (vooral) de schrijfproducten wat betreft de opbouw, de formulering, de werkwoordspelling, de interpunctie en de leesbaarheid. Student kan lijst van geraadpleegde literatuur opstellen.", "Algemeen");
        keuzevak[1] = new Keuzevak("ISTAT", "Statistiek voor IT-ers", "De volgende begrippen en onderdelen uit de statistiek worden onder andere behandeld: • gemiddelde en standaarddeviatie; • normale verdeling; • binomiale verdeling; • betrouwbaarheidsinterval; • nulhypothese en alternatieve hypothese; • fouten van de eerste en tweede soort; • significantie testen. Er wordt aandacht besteed aan kansrekening. Het gebruik van een of meer statistiekpakketten voor het uitvoeren ',", "Business Data Management");
        keuzevak[2] = new Keuzevak("IKITIL", "ITIL", "Diensten op het gebied van IT worden tegenwoordig door veel gespecialiseerde externe bedrijven geleverd. Toch hebben nog steeds veel organisaties een interne IT-afdeling. In alle gevallen staat daarbij een goede IT-dienstverlening voorop, in een steeds sneller veranderende wereld, vol met concurrenten. ITIL (Information Technology Infrastructure Library) is een framework met richtlijnen die aangeven hoe een (externe) IT-dienstverlener het best kan garanderen dat haar klanten de producten en diensten krijgen die zij verlangen. ITIL 4 integreert onder andere Lean, Agile, DevOps en Cloud Computing. en ondersteunt snelle, kwaliteitsgerichte en waardegedreven opleveringen van IT-diensten. De toets bestaat uit een assessment met als basis: een behaalde externe certificering ITIL 4 Foundation óf een onderzoeksverslag gericht op ITIL 4. N.B. literatuur is uitsluitend in het Engels beschikbaar.", "Business Data Management");
        keuzevak[3] = new Keuzevak("IWIS", "Wiskunde voor ICT-ers", "Uit de discrete wiskunde: recurrente betrekkingen en combinatoriek. • Uit de vectoralgebra: vectoren, vectorvoorstellingen en vergelijkingen van lijnen en vlakken, intersectie en het gebruik van het inwendig product van vectoren om hoeken te bepalen. • Uit de numerieke analyse: het bisectie-algoritme om een nulpunt van een continue functie te benaderen met opgegeven nauwkeurigheid. • Uit talstelsels: binaire en hexadecimale notatie van getallen.',", "Software Engineering");
        keuzevak[4] = new Keuzevak("IKPDM", "Programming for mobile devices", "Programming for mobile devices heeft tot doel de studenten kennis te laten maken met ontwikkelen van software in een groep (d.m.v. versioning tools) en meer bijzonder het ontwikkelen van software voor Mobile Devices (Apps). De wereld van App.-ontwikkeling is voortdurend in beweging, dus de weg naar resultaat blijft vooralsnog een hobbelige. De veelheid aan devices, maar ook de keuze van bijv. OSX en Windows (of Linux?) als ontwikkelomgevingen creëert een veelheid aan combinaties. Niet alle combinaties zullen vlekkeloos werken. Hiermee dient de student pragmatisch om te gaan.", "Software Engineering");
        keuzevak[5] = new Keuzevak("IITPS", "IT Psychologie", "IT Psychologie is een specialisatie van de organisatiepsychologie en richt zich op het snijvlak van mens en IT systeem. Het invoeren en gebruiken van IT systemen vereist namelijk vrijwel altijd aanpassingen in het gedrag van mensen. De student kan na deze module een aantal belangrijke thema’s uit de organisatiepsychologie relateren aan het invoeren en gebruiken van IT systemen binnen organisaties. Thema’s die aan bod komen zijn: • attitudes en emoties; • motivatie; • creativiteit en innovatie; • communicatie; • teams en groepsprocessen; • leiderschap. Met deze thema’s is de student beter in staat om de ‘zachte’ kant van IT projecten vorm te geven. De zachte kant, zoals de communicatie met en acceptatie van de gebruikersorganisatie blijkt in de praktijk namelijk bijna altijd de kritieke sleutel om een IT project tot een succes te maken.", "Business Data Management");
        keuzevak[6] = new Keuzevak("IBK6", "Opzetten eigen IT bedrijf", "De student is na (gunstige) afronding van deze module in staat om een ondernemingsplan te schrijven voor een zelf op te richten (IT-)bedrijf.", "Business Data Management");
        keuzevak[7] = new Keuzevak("IFP2", "Functioneel Projectmanagement", "Projectmangement is heden ten dage essentieel om een project in goede banen te leiden. Er zijn vele methoden en aanpakken die gebruikt worden, PRINCE2 en SCRUM zijn twee van de bekendste en meest toegepaste. In deze module wordt stilgestaan bij projectmanagementmethodes en -aanpakken. De toepassing van deze methodes en aanpakken wordt op basis van een boek op een gestructureerde manier voorgeschoteld en uitgelegd. Aan de hand van voorbeelden wordt de theorie onderbouwd. Hierbij wordt aandacht besteed aan de thema's, processen en het op maat maken van deze methoden, waarbij de klantgerichtheid voorop staat. Bij de colleges over PRINCE2 is het belang van een juiste business case de leidraad, bij SCRUM is dat de wendbaarheid tijdens realisatie. Het tentamen bestaat uit multiple choice vragen en is vergelijkbaar met het PRINCE2 Foundation niveau.", "Business Data Management");
        keuzevak[8] = new Keuzevak("IKLO", "Logica", "Logica gaat over het logisch redeneren aan de hand van predicatorenlogica. 'Onderwerpen die aan bod komen zijn: propositielogica, verzamelingenleer, relaties.", "Software Engineering");
        keuzevak[9] = new Keuzevak("ITREWA", "Trend Watching", "Trends zijn overal om ons heen. In een willekeurig straatbeeld vindt men trends van technologieën, apparaten, software, mode en eigenlijk alles wat een mens gebruikt. Wij als mensen zijn er allemaal in zekere mate gevoelig voor. Maar wat zijn trends nou precies? Hoe zijn ze te verklaren? Hoe analyseer ik ze? De antwoorden op dit soort vragen komen aan bod in deze module.", "Algemeen");
        keuzevak[10] = new Keuzevak("IKUE", "Usability Engineering", "Usability (gebruiksgemak) heeft de focus op gebruikersinteractie. Het is noodzakelijk om kennis te hebben van de theorieën die ten grondslag liggen aan usability. Usability begint bij de doelgroep; een goede definitie van en kennis over de doelgroep bepaalt welke usability guidelines van toepassing zijn. Natuurlijk dienen dan ook de diverse usability guidelines die als algemeen gangbaar worden geacht bekend te zijn. Behalve de theorie wordt ook gekeken naar usability in de praktijk: Hoe kunnen we gebruiksgemak meten? Als we in staat zijn een verbetervoorstel te schrijven is het ook noodzakelijk om objectief te kunnen bepalen of de voorgestelde wijzigingen wel daadwerkelijk tot verbeterde usability hebben geleid. Hiertoe moet een mediatechnoloog een usability test kunnen ontwerpen en uitvoeren, wat uiteindelijk cumuleert in een usability testrapport.", "Interactie-technologie");
        keuzevak[11] = new Keuzevak("IKEMA", "Kennis Management", "Kennismanagement behandelt de essentie en waarde van kennis, hoe organisaties dit managen en welke (faciliterende) rol IT hierin kan hebben. Diverse businessmodellen en IT-oplossingen voor het managen van kennis en het stimuleren van kennisdeling in organisaties worden behandeld. Basisconcepten van kennissystemen worden besproken en het verschil met informatiesystemen komt ook aan bod.", "Business Data Management");
        keuzevak[12] = new Keuzevak("IKGEO", "Geografische data / Open Data", "Deze module is geschikt voor studenten van alle specialisaties, die zich willen verdiepen in geografische data en de systemen die met deze data om kunnen gaan. Je leert op welke manier je objecten met een geografisch kenmerk kunt registreren in een Geografisch InformatieSysteem. Je maakt kennis met de rol die zo'n GIS inneemt bij bedrijven en overheidsorganisaties. Daarnaast leer je kaarten te maken, waarop de points of interest gepositioneerd worden en krijg je te maken met visualisatietechnieken om de kaart overzichtelijk te houden", "Business Data Management");
        keuzevak[13] = new Keuzevak("IKFRAM", "Frameworks", "De student leert tijdens deze module een technologie of framework (bijvoorbeeld het laravel framework, het VUE.js framework) door middel van een online udemy-cursus (of vergelijkbare dienst). Daarnaast maakt de student een zelfbedachte applicatie met behulp van deze techniek. Dit doet de student zelfstandig. De techniek / het framework mag niet in het curriculum van de student voorkomen. Zo mogen Interactie-technologie-studenten bijvoorbeeld React niet als onderwerp kiezen. Voor SE-studenten geldt dat Angular niet gekozen mag worden. Na het behalen van certificaat levert de student dit certificaat uiterlijk 2 weken voor het assessment in aan de moduleleider. Hierna wordt een assessment ingepland. Dit assessment bestaat onder andere uit een presentatie over de techniek, de demo-applciaties gemaakt tijdens de cursus en de eigen gemaakte applicatie.", "Software Engineering");
        keuzevak[14] = new Keuzevak("ILNUX1", "Linux 1", "De student behaalt het certificaat LPIC level 1, door middel van externe toetsing. Dit internationaal erkende certificaat toont aan dat de kandidaat op juniorniveau het beheer over Linux systemen kan uitvoeren. Voor het certificaat dienen de examens LPI 101 en LPI 102 met succes afgelegd te worden. Daarna kan de student het assessment aanvragen bij de examinator van deze module om het vak af te ronden.", "Forensisch ICT");
        keuzevak[15] = new Keuzevak("IKBDAM", "Keuzemodule BDAM", "Deze module is speciaal bedoeld voor studenten die niet voor de specialisatie Business Data Management hebben gekozen, maar wel een basis willen meekrijgen. Deze keuzemodule gaat dieper in op de theorie en technologie, die typerend is voor Business Data Management en die niet terugkomt in de andere specialisaties. Naast het volgen van vakinhoudelijke colleges ontwikkel je een klein product, waardoor je de basis van BDaM in de vingers krijgt.", "Business Data Management");
        keuzevak[16] = new Keuzevak("IKMEDT", "Keuzemodule Interactie-technologie", "Deze module is speciaal bedoeld voor studenten die niet voor de specialisatie Interactie-technologie hebben gekozen, maar wel een basis willen meekrijgen. Deze keuzemodule gaat dieper in op een technologie die typerend is voor Interactie-technologie en niet terugkomt binnen de andere specialisaties. Over deze technologie worden lessen gegeven, en ligt de nadruk vooral op het ontwikkelen van een systeem of applicatie. Het ontwikkelen hiervan loopt volgens de ‘Design thinking’ methode. Kort gezegd wordt er bij design thinking gedacht vanuit de eindgebruiker en wordt kort-cyclisch gewerkt naar een eindproduct.", "Interactie-technologie");
        keuzevak[17] = new Keuzevak("IKSE", "Keuzemodule SE", "Veel toepassingen worden via het internet toegankelijk gemaakt via RESTful API’s. De client maakt gebruik van JavaScript frameworks zoals Angular. Beveiliging van webapplicaties is belangrijk.", "Software Engineering");
        keuzevak[18] = new Keuzevak("IKBLOCK", "BlockChain", "Keuzemodule in het teken van Blockchain technologie.", "Business Data Management");
        keuzevak[19] = new Keuzevak("IKDOCK", "Docker", "De module staat in het teken van de DevOps (development operations) technologie Docker. Je leert hier alle basisvaardigheden om een stack in Docker te realiseren. Let op: Het vak wordt als zeer moeilijk ervaren, wij raden dan ook aan dit vak te volgen wanneer je in het 3e jaar zit.", "Software Engineering");
        keuzevak[20] = new Keuzevak("IKREFACT", "Software Refactory", "Keuzemodule in het teken van het refactoren van (eerder gemaakte) software.", "Software Engineering");
        keuzevak[21] = new Keuzevak("IKSNORG", "Sociale netwerken in organisaties", "Keuzemodule in het teken van sociale netwerken in organisaties. Je leert hier o.a. het onderzoeken en in kaart brengen van deze netwerken.", "Algemeen");
        keuzevak[22] = new Keuzevak("IKRESE", "Remote Sensing", "Deze keuzemodule staat in het teken van remote sensing. Remote sensing is het verzamelen van data door objecten die niet rechtstreeks in contact staan met hetgeen wat ze meten. Denk hierbij aan bijvoorbeeld satellieten en radar.", "Business Data Management");
        keuzevak[23] = new Keuzevak("IKCP", "Concepten in programmeertalen", "Let op: voor deze module geldt een ingangseis. De propedeuse (P) en 40 punten uit de hoofd-fase / post-propedeutische fase dienen behaald te zijn. Dit vak gaat over concepten in programmeertalen. Je leert bij dit vak een programmeertaal van binnen en van buiten kennen met alle bijbehorende technieken.", "Software Engineering");
        keuzevak[24] = new Keuzevak("IKFAIR", "FAIR (onderzoeks-)data-principe", "Keuzemodule in het teken van FAIR data-principes.Vak vult het BDAM-curriculum aan.", "Business Data Management");
        keuzevak[25] = new Keuzevak("IPRODAMO", "Proces- en Data-modelleren", "Dit vak gaat over proces- en datamodelatatie. Verdere info volgt.", "Business Data Management");
        keuzevak[26] = new Keuzevak("IKETHA", "Ethical Hacking", "Bijna dagelijks lezen we over aanvallen, die door hackers worden uitgevoerd op computersystemen. Deze module laat je kennismaken met de wereld van de netwerken en hun bedreigingen. Hoe werkt een netwerk-server en waar zitten de zwakke plekken? In de module wordt allereerst veel aandacht besteed aan zogenaamde basis netwerk kennis en de belangrijkste netwerk protocollen. Er wordt veel aandacht besteed aan hoe netwerkbeveiliging te testen door middel van pen-testen (penetratie-testen) ook wel ethical hacking genoemd. Het voorbereiden, uitvoeren en documenteren van een pen-test. Voor pen-testen zal gebruik worden gemaakt van Kali. Kali is een Linux distributie met een omvangrijk pakket aan pen-test tools.", "Forensisch ICT");
        keuzevak[27] = new Keuzevak("IKSMOE", "Semantic Modelling and Ontology Engineering", "Let op: voor deze module geldt een ingangseis. IPRODAMO dient behaald te zijn. “Hé Siri/Alexa/Cortana, welke knaagdieren leven in de omgeving van de uitvinder van de computermuis?” Je stelt deze vraag misschien omdat je benieuwd bent of het zien van knaagdieren de uitvinder heeft geïnspireerd. Maar AI agents zoals Siri, Alexa en Cortana kunnen deze vraag niet beantwoorden. Nog niet. Zelf kun je de vraag beantwoorden door een koppeling te maken tussen het Wikipedia lemma over de computermuis enerzijds en open data over dieren in de omgeving van Portland anderzijds. Voor AI software is het lastig die koppeling te maken. Bij iksmoe gaan we kijken hoe je data op zo’n manier kunt modelleren en opslaan dat het mogelijk wordt om verschillende databronnen aan elkaar te koppelen. Die gekoppelde data, ook wel linked data genoemd, geeft AI agents zoals Siri, Alexa of Cortona de mogelijkheid om veel complexere vragen te beantwoorden. We gaan ook kijken naar een querytaal waarmee je vragen zoals de bovenstaande kunt stellen aan linked datasets.", "Business Data Management");
        keuzevak[28] = new Keuzevak("IKCOVI", "Computer Vision", "Let op: Voor deze module geldt een ingangseis. De propedeuse (P) + IKML / IML behaald te zijn. In de module Computer Vision leer je de basis principes van beeldverwerking (Image Processing). Elk geavanceerd algoritme dat automatisch beelden interpreteert, maakt gebruik van deze technieken om het beoogde doel te bereiken. Denk hierbij aan de google image search, gezichtsherkennings om een mobiele telefoon te ontgrendelen of aan zelfrijdende auto’s. Aan de hand van praktische opdrachten in python, leer je de belangrijkste beeldverwerkingsalgoritmen kennen en toepassen. Deze technieken worden geïmplementeerd door gebruik te maken van bestaande Computer Vision modules.", "Forensisch ICT");

        // Assign variables
        recyclerView = v.findViewById(R.id.recyclerView);

        // Setup Recyclerview
        gridLayoutManager = new GridLayoutManager(getActivity(), 2 ,GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set Adapter for Recyclerview
        recyclerViewAdapter = new KeuzevakAdapter(keuzevak);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.hasFixedSize();

        // Setup Search
        // setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.keuzevak_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(keuzevakAdapter != null) {
                    keuzevakAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}