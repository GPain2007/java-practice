# java-practice

My RevPro / PEP Java bootcamp labs (Aug–Oct 2023), combined from 110 separate repos into one place for practice.

- **`main`**: every lab reset to its original starter code. Solve them again from scratch.
- **`solutions`**: my original answers, for checking after an attempt.

## How to practice a lab

```bash
cd 01-java-basics/FizzBuzz
mvn test          # failing tests = what's left to solve
```

Each lab is its own Maven project (JUnit tests in `src/test/java`). Edit the file(s) listed under **Files you edit**, then rerun `mvn test` until it's green.
To compare with my old answer: `git diff main solutions -- <lab folder>`.

## Labs

### Java basics

| Lab | Files you edit |
| --- | --- |
| [Start](01-java-basics/Start) | `Questions.java` |
| [HelloWorld](01-java-basics/HelloWorld) | `Lab.java` |
| [FizzBuzz](01-java-basics/FizzBuzz) | `Lab.java` |

### Collections

| Lab | Files you edit |
| --- | --- |
| [Stack](03-collections/Stack) | `Lab.java` |

### Arrays, strings & algorithms

| Lab | Files you edit |
| --- | --- |
| [Fibonacci](04-arrays-strings-algorithms/Fibonacci) | `Fibonacci.java` |
| [ReverseAString](04-arrays-strings-algorithms/ReverseAString) | `StringReverse.java` |
| [MostCommonCharacter](04-arrays-strings-algorithms/MostCommonCharacter) | `MostCommonCharacter.java` |
| [LargestSum](04-arrays-strings-algorithms/LargestSum) | `LargestSum.java` |

### SQL via Java (JDBC)

| Lab | Files you edit |
| --- | --- |
| [CreateTable](05-sql-jdbc/CreateTable) | `problem1.sql` |

### Spring & JPA

| Lab | Files you edit |
| --- | --- |
| [SPR-CL-DI](07-spring-and-jpa/SPR-CL-DI) | `ArithmeticPresentationLayer.java`, `Multiplier.java`, `Squarer.java` |
| [SPR-CL-BEANSCOPES](07-spring-and-jpa/SPR-CL-BEANSCOPES) | `Application.java` |
| [SPR-CL-APPLICATIONCONTEXT](07-spring-and-jpa/SPR-CL-APPLICATIONCONTEXT) | `Lab.java` |
| [SPR-CL-RESTCONTROLLER](07-spring-and-jpa/SPR-CL-RESTCONTROLLER) | `SampleController.java` |
| [SPR-CL-REQUESTPARAM](07-spring-and-jpa/SPR-CL-REQUESTPARAM) | `SearchController.java` |
| [SPR-CL-QUERY](07-spring-and-jpa/SPR-CL-QUERY) | `PetRepository.java` |
| [SPR-CL-ENTITY](07-spring-and-jpa/SPR-CL-ENTITY) | `Product.java` |
| [SPR-CL-JPACRUD](07-spring-and-jpa/SPR-CL-JPACRUD) | `StoreService.java` |
| [SPR-CL-TRANSACTIONAL](07-spring-and-jpa/SPR-CL-TRANSACTIONAL) | `ShipService.java` |
| [SPR-CL-JPAMULTIPLICITY](07-spring-and-jpa/SPR-CL-JPAMULTIPLICITY) | `StudentService.java` |
| [SPR-CL-NAMEDQUERIES](07-spring-and-jpa/SPR-CL-NAMEDQUERIES) | `BookRepository.java` |
| [SPR-CL-RELATIONSHIPS](07-spring-and-jpa/SPR-CL-RELATIONSHIPS) | `Album.java` |

### Final projects

| Lab | Files you edit |
| --- | --- |
| [pep-project](08-final-projects/pep-project) | `SocialMediaController.java`, `MessageDAO.java`, `UserDAO.java`, `Account.java`, `MessageService.java`, `UserService.java` |
| [pep-spring-project](08-final-projects/pep-spring-project) | `SocialMediaController.java`, `AccountRepository.java`, `MessageRepository.java`, `AccountService.java`, `MessageService.java` |

## Notes

- Original repos were `GPain2007/GPain2007-<Lab>`. Each lab folder here is that repo's starter commit (on `main`) or final commit (on `solutions`), minus Gitpod/RevPro tooling files.
- In `08-final-projects/pep-project`, some DAO/service classes didn't exist in the starter, so on `main` you create them yourself.
