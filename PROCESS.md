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
