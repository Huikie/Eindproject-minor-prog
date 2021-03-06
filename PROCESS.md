# dag 1 - 8-jan
- Bepaald dat ik voorlopig en misschien zelfs helemaal het registreren van gebruikers weglaat. Het zou namelijk in de beginfase van mijn app geen toegevoegde waarde hebben, maar zou eventueel in een later stadium interessant kunnen worden.
- Google Maps API vastgesteld als juiste API om een map te genereren voor de app.
- OpenWeatherMap API vastgesteld als juiste API om weerinformatie op te vragen voor de app.
# dag 2 - 9-jan
- Er voor gekozen om gebruikers niet in staat te stellen zelf spots toe te voegen, maar hen indirect de mogelijkheid te geven door middel van een aanvraag die zij kunnen doen. Deze aanvraag zal de beheerder (ik) dan afkeuren, goedkeuren of wijzigen en goedkeuren. Hierbij goedkeuring gekregen van assistente (Renske). Haar leek het ook verstandiger om dit zelf te beheren, omdat anders de app een zootje zou kunnen worden.
- Een aantal spots gekozen die vanaf het begin in de app aanwezig zijn.
- Bepaald dat windsnelheid (in knopen), windrichting (in letter en in hoek) en de temperatuur de meest interessante weerinformatie is voor de gebruikers van mijn app.
# dag 3 - 10-jan
- Voorlopig beperkt tot vier activities en deze aangemaakt en met elkaar verbonden: MapsActivity (map & spots), SpotRequestActivity (mogelijkheid tot aanvraag van spot), StartSessionActivity (sessie aanmaken) en SpotDetailsActivity (spot gegevens & sessie informatie).
- Plan van aanpak omtrent gebruik van databases voor de app besproken met assistente (Natasja): minimaal twee ONLINE databases nodig, één voor het opslaan van spotinformatie en één voor het opslaan van sessie informatie.
# dag 4 - 11-jan
- Bepaald dat de aanvragen voor spots door gebruikers in de database met spotinformatie komen en dat de spots in de deze database een parameter "status" krijgen die bepaald of ze zichtbaar zijn in de map of niet. Zo kan de beheerder (ik) deze status via de terminal wijzigen zodat spots zichtbaar worden als ze goedgekeurd zijn.
# dag 5 - 14-jan
- In overleg met assistent (Marijn) besloten om de spots die vanaf het begin in de applicatie te zien zijn handmatig toe te voegen aan de online spotinformatie database door een curl POST operatie in de CS50 terminal. Had de spots ook kunnen hardcoden in de app en dan kunnen posten, maar dit is me afgeraden.
- Besloten om voorlopig af te wachten hoe om te gaan met afbeeldingen voor spots, omdat volgens assistent (Marijn) er mogelijk nog extra functionaliteit in de Rester API geimplementeerd gaat worden die de omgang hier mee makkelijker zou kunnen maken.
# dag 6 - 15-jan
- Besloten om gebruikers bij lang klikken op de map door te verwijzen naar de pagina waar ze een spotaanvraag kunnen doen. Zo kunnen de gebruikers heel precies aangeven waar ze graag een spot willen en ontvang ik de precieze latitude en longitude van de aangevraagde spot. Door een medestudent is mij echter wel aangeraden om gebruikers te informeren over deze lang klik mogelijkheid, omdat het niet voor zich spreekt.
- Besloten om de spotinformatie rechts in de SpotDetailsActivity te weergeven en de afbeelding links. Daarnaast heb ik weerinformatie en andere spotinformatie in twee blokken van elkaar gescheiden en de soort spotinformatie dikgedrukt weergeven.
# dag 7 - 16-jan
- Besloten om gebruikers de mogelijkheid te geven om het kaarttype van de Googlemap te veranderen, omdat ik uit ervaring weet dat de verschillende kaarttypes handig kunnen zijn bij het opzoek gaan naar kitespots.
- De manier van gebruikers informeren over de lang klik mogelijkheid heb ik bepaald en geimplementeerd. Dit heb ik gedaan door rechtsboven in het scherm een ImageButton toe te voegen met een 'addmarkericon'. Als gebruikers daar op klikken krijgen ze informatie hoe ze een spotaanvraag kunnen doen (door lang te klikken op de plek waar je, jouw spot wilt).
# dag 8 - 17-jan
- Op advies van assistent (Renske) besloten om twee extra activiteiten te maken. Één waarin gebruikers alle sessies kunnen zien die momenteel bezig zijn met de sessieinformatie erbij, alfabetisch gesorteerd op spotnaam en op wanneer de sessie gestart is (laatste sessie bovenaan) en één waarin gebruikers alle spots (spots die op de map staan (status = zichtbaar)) en spotaanvragen die gedaan zijn (status = in behandeling)) kunnen zien, aflatbetisch gesorteerd op spotnaam.
- Besloten om zoom in en zoom out Buttons uit de MapsActivity te verwijderen, omdat in een app mensen hoogstwaarschijnlijk zullen zoomen door met twee vingers te slepen.
# dag 9 - 18-jan
- Besloten dat het nodig is om userinput op een goede manier te limiteren, zodat de inhoud van de app netjes blijft. Idee is: bij kitemaat bijvoorbeeld de user aan laten geven hoeveel kites hij/zij meeneemt (stel: 2) en op basis daarvan zoveel inputfields geven aan de user (2 inputfields). Om de tijdspanne in een goed formaat van de user te ontvangen is belsoten om een 'timepicker' te laten zien aan de gebruiker voor zowel de begin als de eindtijd. De tijdspanne wordt dan ontvangen in het volgende formaat: HH-mm - HH-mm. Bij een spotaanvraag wordt de gebruiker ook om input gevraagd, waaronder spottype en spotondergrond. Hiervoor is besloten een lijstje van strings te maken met mogelijke types en ondergronden waaruit de gebruiker de mogelijkheid kiest om één ondergrond en type te selecteren.
# dag 10 - 21-jan
- Besloten om Base64 encoding te gebruiken om afbeeldingen up te loaden naar de API en vervolgens te ontcijferen d.m.v. Base64 decoding als de afbeeldingen van de server nodig is. Ook besloten dat wanneer een gebruiker een afbeelding upload het nodig is dat de gebruiker ziet wat hij/zij geupload heeft door het te weergeven op het scherm.
- Nagedacht over kleurschema's voor de applicatie en bepaald dat #26b1e7 een mooie basiskleur is voor de app.
- Besloten om een refresh icon te gebruiken in plaats van een button met 'ververs' erin, zodat de app er mooier uit komt te zien.
# dag 11 - 22-jan
- Kleurenpatroon gevonden dat mij mooi lijkt voor de applicatie: https://colorhunt.co/palette/2763. Voor 2 activiteiten geïmplementeerd en nu nog voorleggen aan verschillende mensen voordat ik het definitief implementeer in alle activiteiten.
- Besloten om 'items' in 'ListViews' om de beurt een andere kleur te geven, zodat de lijst niet één groot blok wordt.
- Besloten om een limiet van 0.5 mb te stellen op de grote van afbeeldingen die gebruikers mogen uploaden bij het doen van een spotaanvraag. De reden hiervoor is dat ik Base64 encoding gebruik en hoe groter de afbeelding des te langzamer het laden van de spots wordt, omdat dan de Base64 string die uit de database gelezen moet worden ook groter is.
# dag 12 - 23-jan
- Besloten om titels in #00adb5 dikgedrukt te doen met een lettertype van 30dp. De achtergrond #222831 gemaakt en de statusbar #393e46 gemaakt. Voor normale tekst heb ik #eeeeee als kleur gekozen en een lettertype van 18dp, waarbij sommige teksten die er meer uit moeten springen 20dp zijn of dikgedrukt of schuingedrukt. Daarnaast besloten om de titelbar weg te laten, omdat de titelbar geen toegevoegde waarde heeft voor de gebruiker. Ook gekozen om alle elementen minimaal 12dp van de kant af te halen om de leesbaarheid te vergroten en titels 24dp padding te geven, zodat deze er goed uitspringen.
# Hackathon - 24-jan
# dag 13 - 25-jan
- Besloten om drie verschillende manieren van errors weergeven bij missende of verkeerde userinput te gebruiken. Voor userinput in EditTetxs wordt een functie setError(string) gebruikt. Dit resulteert in een klein rood icoontje die als je er op klikt laat zien wat vereist is in het input veld. Bij het kiezen van de begin en eindtijd door de gebruiker leverde deze manier geen mooie lay-out op waardoor ik ervoor gekozen heb om de TextViews die aangeven wat er gekozen moet worden (begin en eindtijd) in een TextInputLayout te stoppen waardoor het mogelijk wordt om een custom error message onder deze TextViews te weergeven als er verkeerde input wordt gegeven. Daarnaast voor errors bij het uploaden van afbeeldingen heb ik gekozen om TextViews met daarin een error message normaal gesproken op 'visibility gone' zet en wanneer het nodig is maak ik deze berichten 'visible'. 
# dag 14 - 28-jan
- Besloten om een korte vertraging te maken nadat gebruikers een sessie gestart hebben en nadat gebruikers een spotaanvraag gedaan hebben. In deze vertraging laat ik een bericht zien dat de handeling succesvol verricht is op/voor de specifieke spot. Was ook mogelijk geweest om ze terug te sturen naar respectievelijk de SpotDetailsActivity (waar sessies op de spot te zien zijn) en de SpotStatusActivity (waar alle spots te zien zijn ook de aangevraagde), maar door tijdgebrek dit laten zitten.
- Besloten om aan alle ListViews een duidelijke scrollbar toe te voegen, zodat duidelijk is dat er meer informatie onder staat en dat dit bereikt kan worden door te scrollen.
# dag 15 - 29-jan
- Besloten om in de 'Infowindow' van een spot (marker) achter de titel '>>' te plaatsen, zodat duidelijk is dat er op deze Infowindow geklikt kan worden om informatie over deze spots te zien te krijgen.
- Besloten om bij alle 'Requests' een error te geven in de vorm van een 'Toast' als de Request niet succesvol verloopt.
# dag 16 - 30-jan
- Besloten om bij het laden van de spots in de MapsActivity en de SpotStatusActivity een laad icoontje te laten zien en de tekst 'Spots worden geladen!', omdat door de Base64 encoding van de afbeeldingen het laden van deze spots een beetje langzaam gaan. Met name als er heel veel spots in de database staan.
