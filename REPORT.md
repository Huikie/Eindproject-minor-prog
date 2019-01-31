# KiteSessiesNL App

## Ontwikkelaar: Daan Huikeshoven (11066628)

## Beschrijving van de app
In deze app kunnen kiters een sessie starten op spots die aanwezig zijn in de app. Daarnaast kunnen ze sessies van andere kiters zien die sessie gestart hebben. Ook kunnen ze een aanvraag doen om hun spot op de kaart te krijgen waarna ze de status van deze aanvraag in de app in de gaten kunnen houden.<br><br>
![](https://github.com/Huikie/Eindproject-minor-prog/blob/master/doc/home.png)

## Technische design
#### Overzicht van activiteiten & API's
![](https://github.com/Huikie/Eindproject-minor-prog/blob/master/doc/report_design.png)
#### Details: modules, request classes en adapters
Modules/classes:<br>
- Session module: wordt gebruikt om alle atrributen van een sessie te definiëren. De session module bevat de volgende attributen: name, kite, time, spot, date en exactDate.
- Spot module: wordt gebruikt om alle attributen van een spot te definiëren. De spot module bevat de volgende attributen: name, type, surface, distance, imageId, directionId, lat, lon en status. 'Name' is in de spot module het zelfde als 'spot' in de session module, namelijk de spot naam.
- WeatherInfo module: wordt gebruikt om alle atrributen die horen bij de benodigde weerinformatie te definiëren. De weatherinfo module bevat de volgende attributen: speed, degrees en temperature.
Adapters:<br>
- SessionAdapter: wordt gebruikt om de lijst in de SpotDetailsActvitiy met de juiste elementen en in een mooie stijl te weergeven.
- SessionAdapter2: wordt gebruikt om de lijst in de CurrentSessionActivity met de juiste elementen en in een mooie stijl te weergeven.
- SpotAdapter: wordt gebruikt om de lijst in de SpotStatusActivity met de juiste elementen en in een mooie stijl te weergeven.
Request classes:<br>
- SessionPostRequest: wordt gebruikt nadat een sessie gestart is in de CreateSessionActivity om de sessie in de database te krijgen.
- SessionRequest: wordt gebruikt om sessies uit de database te krijgen. Wordt gebruikt in de SpotDetailsActivity om de 'Sessies vandaag' lijst te vullen met sessies vandaag op een specifieke spot en in de CurrentSessionActivity om alle sessies die vandaag gaande zijn te weergeven in een lijst.
- SpotPostRequest: wordt gebruikt nadat een spotaanvraag gedaan is in de SpotRequestActivity om de spotaanvraag in de database te krijgen.
- SpotGetRequest: wordt gebruikt om spots uit de database te krijgen. Wordt gebruikt in de MapsActivity om de kaart te voorzien van markers voor de spots die een status = 1 (approved) in de database hebben en in de SpotStatusActivity om een lijst te vullen met alle spots inclusief de spots die nog in behandeling zijn (niet approved).
## Uitdagingen gedurende ontwikkeling van de app
