# Air Nex Control System

## Temat projektu: *System obsługi lotniska*

## Autorzy
- Maciej Deroń
- Paweł Dostal
- Mariusz Ignaciuk
- Julia Dziekańska

## Założenia dodatkowe
Projekt jest realizowany przy użyciu języka programowania Java, frameworka Spring Boot oraz narzędzia do zarządzania projektem Maven. Frontend projektu jest realizowany przy użyciu thymeleafa.

## Opis architektury
Architektura systemu jest oparta na modelu MVC (Model-View-Controller). Każdy z głównych komponentów systemu (Plane, Flight, Ticket, Passenger, Airline, Airport) ma swoje repozytorium, serwis i kontroler. Dodatkowo, dla każdego z tych komponentów tworzone są odpowiednie obiekty DTO (Data Transfer Object), komendy oraz wyjątki.

## Schemat bazy danych
![Schemat bazy danych](img/airnexcontrolsystem-database.png)

## Testowanie
 - Aby uruchomić bazę danych należy wykonać polecenie `docker-compose up` w głównym katalogu projektu.
 - Aby uruchomić aplikację należy wykonać polecenie `mvn spring-boot:run` w głównym katalogu projektu. Aplikacja będzie dostępna pod adresem `http://localhost:8080/`.
 - Aby uruchomić testy jednostkowe należy wykonać polecenie `mvn test` w głównym katalogu projektu.
 - Testy oraz aplikację można uruchomić również z poziomu IDE.
 - Aby zatrzymać bazę danych należy wykonać polecenie `docker-compose down` w głównym katalogu projektu.
 - Aby zatrzymać aplikację należy wykonać kombinację klawiszy `Ctrl+C` w konsoli, w której została uruchomiona aplikacja.
 - Aby testy przeszły pomyślnie należy uruchomić bazę danych, a następnie testy.

## Podział prac

### Maciej Deroń
- Utworzenie projektu
- Zaprojektowanie bazy danych
- Implementacja encji + testy
- Implementacja value objects + testy
- Implementacja converterów
- Implementacja exception handlerów
- Implementacja Plane(Repository, Service, Controller) + potrzebne DTO, Komendy, wyjątki
- Implementacja Flight(Repository, Service, Controller) + potrzebne DTO, Komendy, wyjątki
- Frontend

### Paweł Dostal
- Implementacja Airport(Repository, Service, Controller) + potrzebne DTO, Komendy, wyjątki
- Współimplementacja Ticket(Repository, Service, Controller) + potrzebne DTO, Komendy, wyjątki
- Testy jednostkowe Airport Service
- Testy jednostkowe Plane Service

### Mariusz Ignaciuk
- Implementacja Airline(Repository, Service, Controller) + potrzebne DTO, Komendy, wyjątki
- Współimplementacja Ticket(Repository, Service, Controller) + potrzebne DTO, Komendy, wyjątki
- Testy jednostkowe Airline Service
- Testy jednostkowe Flight Service

### Julia Dziekańska
- Implementacja Passenger(Repository, Service, Controller) + potrzebne DTO, Komendy, wyjątki
- Współimplementacja Ticket(Repository, Service, Controller) + potrzebne DTO, Komendy, wyjątki
- Testy jednostkowe Passenger Service
- Testy jednostkowe Ticket Service

