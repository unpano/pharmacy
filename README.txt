-----POPUJAVANJE BAZE-----

Baza se kreira na localhost:3306/pharmacy. pharmacy je naziv seme baze podataka. U application.properties fajlu
podesiti:
spring.datasource.username=ovdeIdeUsername
spring.datasource.password=ovdeIdeSifra
Tako da odgovara korisnickom imenu i sifri koju ste koristili prilikom otvaranja konekcije na tom portu. Da bi se
baza kreirala potrebno je kreirati semu sa nazivom pharmacy.

Za ulogu student 1 formirano je 13 tabela u bazi koje se popunjavaju podacima iz resource fajla.
U njemu se nalaze insert naredbe. Nazivi tabela su:
	1. loyalty_program
	2. pharmacies
	3. meds
	4. pharmacy_med
	5. users
	6. authority
	7. user_authority
	8. user_allergy
	9. reservation
	10. term
	11. derm_appointment
	12. prescriptions
	13. prescription_med
Tim redom i popunjavati podatke u tabelama.
Sifre za korisnike su iste kao i username-ovi samo su kriptovane.

-----SLANJE EMAIL-A-----

Za slanje email-a podesiti u application.properties fajlu email i password.
spring.mail.username=ovdeIdeEmailPosiljaoca
spring.mail.password=ovdeIdeSifraPosiljaoca

-----POKRETANJE FRONTEND-A-----

Pozicionirati se u folder \Pharmacy\client i pokrenuti sledece komande.
npm install -g @angular/cli
npm install
npm start

-----POKRETANJE BACKEND-A-----

Otvoriti projekat u IntelliJ-u. Podesiti konfiguraciju da prepozna main class-u. Pokrenuti na Run.
Aplikacija se pokrece na portu 8084. Nije preporucljivo menjati port, jer je front takodje podesen da gadja
taj port, u suprotnom moralo bi se i tamo menjati.



