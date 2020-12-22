PathSrc = ./src
PathBin = ./bin

PathMain = ./src/KakuroMain.java
PathConsole = ./src/ConsoleMain.java
PathControlador = ./src/Domain/Controllers/*.java
PathClasses = ./src/Domain/Classes/*.java
PathPersistencia = ./src/Persistence/*.java
PathException = ./src/Exceptions/*.java
PathPresentacio = ./src/Presentation/*.java
PathLoginScreen = ./src/Presentation/LoginScreen/*.java
PathMainScreen = ./src/Presentation/MainScreen/*.java
PathMenus = ./src/Presentation/MainScreen/Menus/*.java
PathPlayPanel = ./src/Presentation/MainScreen/PlayPanel/*.java
PathImages = ./src/Presentation/img

PathTests = ./src/Domain/Tests/*.java
PathJunit = ./src/Domain/Tests/Junit/*.java 
PathJunitBoardTest = Domain/Tests/Junit/BoardTest
PathJunitBoardRunner = Domain/Tests/Junit/TestRunnerBoard
PathJunitCellTest = Domain/Tests/Junit/CellTest
PathJunitCellRunner = Domain/Tests/Junit/TestRunnerCell


PathDBoard = Domain/Tests/DriverCtrlBoard
PathDGame = Domain/Tests/DriverCtrlGame
PathDViolations = Domain/Tests/DriverCtrlRuleViolations
PathDSolver = Domain/Tests/DriverCtrlSolver

PathKakuroMain = KakuroMain
PathConsoleMain = ConsoleMain

default: all

all:
	javac -d $(PathBin) -sourcepath $(PathSrc) $(PathClasses) $(PathControlador) $(PathPersistencia) $(PathException) $(PathPresentacio) $(PathMain) $(PathConsole) $(PathTests)
	 cp -r $(PathImages) ./bin/Presentation
	 	
compileMain: all
	javac -d $(PathBin) -sourcepath $(PathSrc) $(PathClasses) $(PathControlador) $(PathPersistencia) $(PathException) $(PathPresentacio) $(PathMain) $(PathConsole) $(PathLoginScreen) $(PathMainScreen) $(PathMenus) $(PathPlayPanel)

run: compileMain
	java -cp $(PathBin) $(PathKakuroMain)

runConsole: compileMain
	java -cp $(PathBin) $(PathConsoleMain)
	
compiletests: 
	javac -d $(PathBin) -sourcepath $(PathSrc) $(PathTests)

compileJunits:
	javac -d $(PathBin) -cp junit-4.13.1.jar:hamcrest-core-1.3.jar -sourcepath $(PathSrc) $(PathJunit)

runJunitBoard:compileJunits
	java -cp junit-4.13.1.jar:$(PathBin):hamcrest-core-1.3.jar $(PathJunitBoardRunner) $(PathJunitBoardTest)

runJunitCell:compileJunits
	java -cp junit-4.13.1.jar:$(PathBin):hamcrest-core-1.3.jar $(PathJunitCellRunner) $(PathJunitCellTest)

runDriverBoard: compiletests
	java -cp $(PathBin) $(PathDBoard)

runDriverGame: compiletests
	java -cp $(PathBin) $(PathDGame)

runDriverViolations: compiletests
	java -cp $(PathBin) $(PathDViolations)

runDriverSolver: compiletests
	java -cp $(PathBin) $(PathDSolver)


clean:
	rm -f -r ./bin/