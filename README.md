# Eindproject-minor-prog - KiteSessiesNL

## Naam: Daan Huikeshoven (11066628)

## Paragraph summary
  Mijn voorstel is om een app te maken voor kitesurfers. Kitesurfers kunnen deze app gebruiken om informatie te krijgen en te delen over een spot waar zij gaan kiten. De app zal voorlopig beperkt worden tot spots in Nederland. In de app zullen een aantal veel bezochte kitespots aanwezig zijn (Scheveningen, Wijk aan Zee, Noordwijk etc). Gebruikers kunnen daarnaast een aanvraag indienen om een spot toe te voegen aan de app.
  
## Problem statement

### KitesessiesNL
Kitesurfers kunnen deze app gebruiken om informatie te krijgen en te delen over een spot waar zij gaan kiten. De app zal voorlopig beperkt worden tot spots in Nederland. In de app zullen een aantal veel bezochte kitespots aanwezig zijn onder andere Scheveningen, Wijk aan Zee en Noordwijk. Daarnaast kan een kiter een aanvraag doen om een bepaalde spot (met informatie over de spot) toe te voegen aan de app. Als deze aanvraag geaccepteerd wordt, kunnen alle gebruikers van de app als ze bij deze spot gaan kiten aangeven dat ze daar gaan kiten en eventueel aangeven met welke maat kite ze gaan, hoe laat ze gaan etc. Op deze manier kunnen kiters elkaar informeren om zo een fijnere kitesessie te hebben. Bijvoorbeeld, doordat men aangeeft met welke maat kite hij/zij van plan is te gaan kiten, kan dit twijfel bij mensen (beginners) wegnemen omtrent welke maat kite ze mee moeten nemen en doordat zichtbaar is hoeveel mensen er gaan kiten op welke tijden zou je als beginner de drukte kunnen vermijden of kunnen constateren dat er teveel kiters zijn, zodat je kan beslissen om naar een andere spot te gaan.

### Schets
- https://github.com/Huikie/Eindproject-minor-prog/blob/master/DESIGN.md

## Prerequisites

### Data sources
- Google Maps API
- OpenWeatherMap API (om weer en wind op de spot te tonen aan de gebruiker)

### External components
- Online database met alle spots: https://ide50-huikie.legacy.cs50.io:8080/spotList
- Online database met alle sessies: https://ide50-huikie.legacy.cs50.io:8080/sessionList

### Similar
- http://www.kitetracker.com/gps/livemap > Kitetracker is een website, maar ik kon geen vergelijkbare app vinden. Kitetracker heeft een functionaliteit die aardig overeen komt met het gebruik van een app. Kitetracker laat mensen op een bepaalde LOCATIE een sessie aanmaken en geeft dan voornamelijk informatie over de kiter. Ik ben juist van plan kiters op bepaalde SPOTS een sessie aan te laten maken. Er kunnen dan sessies gestart worden op de spots die aanwezig zijn in de app en gebruikers kunnen een spotaanvraag doen, zodat hun spot op de kaart komt en ze daar een sessie kunnen starten. Ook wordt in mijn app zowel informatie over de spot getoond, als informatie over de aanwezige kiters (anders dan Kitetracker). Daarnaast wil ik actuele weerinformate laten zien op de verschillende spots (heeft Kitetracker niet).

### Hardest parts
- Het nodig hebben van minimaal twee online databases. Dit hebben we echter eerder gedaan bij het maken van de Trivia app. Nu zal de interactie met deze databases echter wel wat complexer worden.

### Attribution
#### Map
Alle bronnen hieronder zijn gebruikt in bestanden uit de onderstaande map (link):
https://github.com/Huikie/Eindproject-minor-prog/tree/master/app/src/main/java/com/example/daan/kitesessiesnl

- Time picker voor het kiezen van een begin en eindtijd bij het starten van een sessie:<br>
  https://www.codingdemos.com/android-timepicker-edittext/<br>
- Instellen van een vertraging nadat sessie gestart is en nadat spotaanvraag gestart is:      <br>https://stackoverflow.com/questions/7965494/how-to-put-some-delay-in-calling-an-activity-from-another-activity
- Ordenen van ListViews:<br>
  https://stackoverflow.com/questions/16192244/java-comparators-for-a-class-in-another-class <br>https://stackoverflow.com/questions/4805606/how-to-sort-by-two-fields-in-java<br>
  https://www.youtube.com/watch?v=hncd_WgF83c
- Pop-up scherm voor informatie over hoe een spotaanvraag gedaan kan worden:<br>
  https://www.youtube.com/watch?v=fn5OlqQuOCk
- Afwisselende achtergrondkleuren van ListView items:<br>
  https://android--code.blogspot.com/2015/08/android-listview-alternate-row-color.html
- Tegengaan dat POST request twee keer gedaan wordt:<br>
  https://stackoverflow.com/questions/27873001/android-volley-sending-data-twice
- Gebruikers in staat stellen afbeeldingen te uploaden:<br>
  https://stackoverflow.com/questions/9107900/how-to-upload-image-from-gallery-in-android
- Upgeloade afbeeldingen omzetten naar een Base64 encoded String:<br>
  https://stackoverflow.com/questions/16291800/converting-image-from-imageview-to-base64-string<br>
  https://stackoverflow.com/questions/9436103/android-util-base64-encode-decode-flags-parameter
- Grootte van afbeeldingen krijgen:<br>
  https://stackoverflow.com/questions/9316986/how-to-get-the-size-of-an-image-in-android
- Windrichting (letter) horende bij een bepaalde hoek vanuit waar de wind komt:<br>
  https://uni.edu/storm/Wind%20Direction%20slide.pdf
> !Deze bronnen kunnen ook gevonden worden op de specifieke plekken in mijn code waar ze gebruikt zijn.!
