# Simple Saga Pattern Implementation

## Two strategies:
    2 steps -> order created and order completed
    3 steps -> order created, inventory deduced and payment processed

### Check Sonarqube Reports

#### 1. Start Sonarqube
```bash
docker-compose up
```

#### 2. Configure token entering
```bash
http://localhost:9000
```
#### 3. Enter token in pom.xml
```bash
   <properties>
        <sonar.login>token</sonar.login>
    </properties>
```

#### 4. Run 
```bash
mvn clean test
mvn sonar:sonar
```

#### 5. Check report in,
```bash
http://localhost:9000
```
