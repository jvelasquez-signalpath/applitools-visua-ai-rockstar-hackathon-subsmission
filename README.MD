# Javier's Hackithon Submission

## Chromedriver

The chromedriver expects Chrome `78.0.3904.108`.

The binary can be updated via https://chromedriver.chromium.org/downloads.
Replace the project binary at `./src/test/resources/chromedriver_mac`

## Requirements

* Java 1.8.x.x
* Mac
* Chrome (see above) 

## Configuration

    export APPLITOOLS_API_KEY="your api key"
 
## Running

### Gradle

#### TraditionalTests

    ./gradlew -Dtest.single=TraditionalTests test
    
#### VisualAI Tests

    APPLITOOLS_API_KEY="your api key" ./gradlew -Dtest.single=VisualAITests test
