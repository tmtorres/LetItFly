Let It Fly
-----------------------
Team Members:
Albert Lansang - acl587
Thomas Torres - tmt696

-----------------------
Instructions:
1. From the title screen, press button to throw something.
2. Next screen shows preloaded objects. You may select any of these objects to throw.
3. On the same screen, there is an "add" button in the upper right corner if you wish to input your own objects, putting in values for name and weight (String, Double).
4. A user may also be able to delete an object by pressing and holding an object's name until the delete dialog appears.
4. After selecting an object, the aim screen appears. Point the devices in the direction in which you want the object to be thrown.
5. After the next screen appears, press and hold anywhere on the screen for the duration of your throwing motion.
6. Once you released your press from the screen, the object is in "flight" and the map view screen is displayed showing your results.
7. You may press the back button to throw the same object again or press the upper right button to go back to the object selection screen to pick a new object.

Features Completed:
- Have preloaded objects and functionality to add/delete objects
- Gathers information from sensors such as GPS location, direction, and acceleration
- Implemented a Google Map View to view starting and landing positions of the object.
- Normalized acceleration (and other values) to compensate for device limits and realistic results

Features NOT Completed:
- retrieve angle at which the object is thrown using sensors
- storing results in library for longest distance thrown for each object
- ability to share to social media

Features Added:
- Loading screen between throwing and the map view which simulates the object in flight.
- Will replace this screen with some sort of animation in future release

Referenced Code:
- We did not use any classes from outside resources, but referenced snipits of code for methods/functions to get GPS location and coordinates from StackOverflow and followed tutorials on the Android Developer Websites
- Also, used SensorTest from our in class lecture to retrieve sensor information

Original Code:
- Physics.java is where the main calculations and results are formed. This class includes basic physics equations, along with normalizers that we derived by trial and error.
- AimActivity.java and ThrowActivity.java contain most of the sensor gathering code.
- All other activities are UI driven, basically to display results given by sensor readings and calculated results from physics.java. 