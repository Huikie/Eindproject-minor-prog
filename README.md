# Eindproject-minor-prog

## Naam: Daan Huikeshoven (11066628)

## Paragraph summary
  Mijn voorstel is om een app te maken voor kitesurfers. Kitesurfers kunnen deze app gebruiken om informatie te krijgen en te delen over een spot waar zij gaan kiten. De app zal voorlopig beperkt worden tot spots in Nederland. In de app zullen een aantal veel bezochte kitespots aanwezig zijn (Scheveningen, Wijk aan Zee, Noordwijk etc). Gebruikers kunnen daarnaast een aanvraag indienen om een spot toe te voegen aan de app.
  
## Problem statement

### KitesessiesNL
Kitesurfers kunnen deze app gebruiken om informatie te krijgen en te delen over een spot waar zij gaan kiten. De app zal voorlopig beperkt worden tot spots in Nederland. In de app zullen een aantal veel bezochte kitespots aanwezig zijn onder andere Scheveningen, Wijk aan Zee en Noordwijk. Daarnaast kan een kiter een aanvraag doen om een bepaalde spot (met informatie over de spot) toe te voegen aan de app. Als deze aanvraag geaccepteerd wordt, kunnen alle gebruikers van de app als ze bij deze spot gaan kiten aangeven dat ze daar gaan kiten en eventueel aangeven met welke maat kite ze gaan, hoe laat ze gaan etc. Op deze manier kunnen kiters elkaar informeren om zo een fijnere kitesessie te hebben. Bijvoorbeeld, doordat men aangeeft met welke maat kite hij/zij van plan is te gaan kiten, kan dit twijfel bij mensen (beginners) wegnemen omtrent welke maat kite ze mee moeten nemen en doordat zichtbaar is hoeveel mensen er gaan kiten op welke tijden zou je als beginner de drukte kunnen vermijden of kunnen constateren dat er teveel kiters zijn, zodat je kan beslissen om naar een andere spot te gaan.

### Schets
- Staat in DESIGN.md.

## Prerequisites

### Data sources
- Google Maps API
- OpenWeatherMap API (om weer en wind op de spot te tonen aan de gebruiker)

### External components
- Online database met alle spots: https://ide50-huikie.legacy.cs50.io:8080/spotList
- Online database met alle sessies: https://ide50-huikie.legacy.cs50.io:8080/sessionList

### Similar
- http://www.kitetracker.com/gps/livemap > Kitetracker laat mensen op een bepaalde locatie een sessie aanmaken en geeft dan voornamelijk informatie over de kiter. Ik ben van plan kiters op bepaalde spots een sessie aan te laten maken en zowel informatie over de spot te tonen als informatie over de aanwezige kiters.

### Hardest parts
- Het nodig hebben van minimaal twee online databases. Dit hebben we echter eerder gedaan bij het maken van de Trivia app. Nu zal de interactie met deze databases echter wel wat complexer worden.
