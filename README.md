## Dungeon

### Overview

This is a model of a 2-D dungeon, composed by caves and tunnels. It needs to represent different kinds of location, treasures, players, and other attributes. I designed a hierachy sturcture and enumeration denotion to help construct this model.

### Features
* Calculator. 
    There is a `GameCalculator` interface that does all the 'dirty' work of calculating, and setting the Players & DungeonMap interfaces free from tedious processes. All changes to the model (except initilization) should go through it.=s implementation.
* Grid Generator
    To seperate the Kruskal Algorithm and other issues with the map design with its own methods, I introduced the `GridGenerator` interface to do these dirty work. It's extendable if you have new algorithm to do so.
* Clear Structure
    The structure has been re-designed so it's very clear and readable.
* Good Extension. 
    In future, it's very easy to extend with other Calculators. Just implement new Classes and replace it in the Game Model.
* Independent Random Helper
    All random processes are summed into the RandomHelper and easy for extending or modifying.

### How to run

* Run the driver:

    Right-click the `model.game.DungeonGameDriver` and run the application. The outcome result will be presented in the `./res/runningResult.txt`. <br />

* Run the JAR file:

    If you have JRE installed on your environment, Double-Click the runnable jar`./res/runnable/p3-dungeon-runnable.jar` is fine enough.<br/>
    Or if you havn't installed, then in the project root file's terminal, put in command
    `java -jar ./res/runnable/p3-dungeon-runnable.jar` <br />
    *Note*: Don't delete the `./res/runnable/res` folder! It's critical.
    The output will be in `./res/runnable/res/runningResult.txt` or `./res/runningResult.txt`.

### Useage

You are encouraged to import the public classes and discover the extendable features in it. After you initialize the players, you can call the calculator to do all the rest work. Like `model.game.GameCalculator` and call all its functions. <br />
There are several functions in it that will return complicated Map Object, because we designed it to be so for multi-layer information.<br />
It would be a great idea to `import org.json.JSONObject;` by configuring `Maven` and call 
```
JSONObject json = new JSONObject(map);
System.out.println(json);
```
to see the bigger image of the Map structure.<br />

### Examples

Location: `./res/runningResult.txt` <br />
The Example basically initiated the whole game with one player named 'Tonnie', printing out the player's attributes & treasures. Then we can initialize a map by the Game Calculator (NOT by the DungeonMap directly) and started the game of walking around randomly, and print the directions. The running result is then printed. <br />
Notice that after we enter the dungeon with the player, we then do random walk and after about 600 steps, see if we can reach the end or not (it could fail if the end is very far). <br />
We also printed the map with details in it for easy reading. You can change the parameters in the `setUp3()` function. <br />

### Design

One BIG change I made was I added a whole new Interface : GridGenerator. Because I realized that the initialization of the map is very complicated, and it has several approaches. Then the better way to do so is have a seperate interface to generate the grid per request, and pass into the dungeon map to reset the directions set. This way we can easily implement other generators with different algorithms, and keep it clean for the DungeonMap interface. <br>
By doing so, our DungeonMap implementations are seperated from the calculation work or caring about connectivity or steps, only maintaining the model datas.

### Assumptions

* Picking up treasures means that the player picking up all the treasures in the location.
* Positive connectivity can be accomplished by simply adding respective amount of edges to the MST.
* The game model will initialize the player information by itself, we do not include that in the Dungeon Model.
* Player can pause at a tunnel.

### Limitations

* If had more time, I would do more work on the boundary check, for instance what if the grid does not match the r & c variables? I encapsuled all these execution in the Game Calculator interface so so far so good. But more checks would be better.

### Citations

Ruidi Huang's representation of the map mentioned in the note : [piazzaLink](https://piazza.com/class/l7polvh6ntw4hj/post/52). I referred to this for my data representation.