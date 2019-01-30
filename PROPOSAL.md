# Eindproject-minor-prog

## Naam: Daan Huikeshoven (11066628)

## Paragraph summary
  Mijn voorstel is om een app te maken voor kitesurfers. Kitesurfers kunnen deze app gebruiken om informatie te krijgen en te delen over een spot waar zij gaan kiten. De app zal voorlopig beperkt worden tot spots in Nederland. In de app zullen een aantal veel bezochte kitespots aanwezig zijn (Camperduin, Noordwijk aan Zee, Brouwersdam etc). Gebruikers kunnen daarnaast een aanvraag indienen om een spot toe te voegen aan de app.
  
## Problem statement

### KitesessiesNL
Kitesurfers kunnen deze app gebruiken om informatie te krijgen en te delen over een spot waar zij gaan kiten. De app zal voorlopig beperkt worden tot spots in Nederland. In de app zullen een aantal veel bezochte kitespots aanwezig zijn onder andere Camperduin, Noordwijk aan Zee en Brouwersdam. Daarnaast kan een kiter een aanvraag doen om een bepaalde spot (met informatie over de spot) toe te voegen aan de app. Als deze aanvraag geaccepteerd wordt, kunnen alle gebruikers van de app als ze bij deze spot gaan kiten aangeven dat ze daar gaan kiten en eventueel aangeven met welke maat kite ze gaan, hoe laat ze gaan etc. Op deze manier kunnen kiters elkaar informeren om zo een fijnere kitesessie te hebben. Bijvoorbeeld, doordat men aangeeft met welke maat kite hij/zij van plan is te gaan kiten, kan dit twijfel bij mensen (beginners) wegnemen omtrent welke maat kite ze mee moeten nemen en doordat zichtbaar is hoeveel mensen er gaan kiten op welke tijden zou je als beginner de drukte kunnen vermijden of kunnen constateren dat er teveel kiters zijn, zodat je kan beslissen om naar een andere spot te gaan.
## Solution
- Kiters kunnen elkaar informeren om zo een fijnere sessie te hebben.
#### Main features:<br>
1) MVP (besproken met Renske):<br>
- Informatie over aanwezige spots kunnen bekijken.<br>
- Sessie op deze spots kunnen starten.<br>
- Spotaanvraag kunnen doen.<br>
2) extra:<br>
- Gebruikers afbeelding laten uploaden bij spotaanvraag.
#### Schets
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
- Het versturen en ophalen van afbeeldingen naar/uit de online database.
