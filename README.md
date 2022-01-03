# Android-Study-Jams
Story Teller App

**Problem Statement**
Alot of people want to read stories this is a small solution that one can read the stories with a titled pic.

**Proposed Solution**

One can read some new exciting stories through this app ,pick some random stories also save their experiences and also can rate us.


![Screenshot_1640268410](https://user-images.githubusercontent.com/80695826/147252539-4cf9de5d-bd86-4085-af90-79e820574a51.png)
![Screenshot_1640268414](https://user-images.githubusercontent.com/80695826/147252548-acf7011f-8d06-414a-99c7-dd0e87049be0.png)
![Screenshot_1640268424](https://user-images.githubusercontent.com/80695826/147252554-50e1af51-42f1-4cf6-a3ff-da4dac655e79.png)
![Screenshot_1640268430](https://user-images.githubusercontent.com/80695826/147252569-71d6c6df-2e37-4f8f-bd8a-f2e4267124dd.png)
![Screenshot_1640438658](https://user-images.githubusercontent.com/80695826/147386087-9cb22106-b8c5-4e35-b219-ce8b52fad231.png)
![Screenshot_1640438673](https://user-images.githubusercontent.com/80695826/147386089-a0814962-2576-4103-b624-d93828386861.png)

1. This is the Splash screenshownfor 2 seconds when we open the app
2. Main Screen where all the stories are already there it also has an image button when clicked on this user will be taken too add Experience layout.
3. Navigation screen where user can click on options like
  a. Home : to enter Main screen
  b. Random Story : will take you to any random story
  c. Rate us : to rate the app out of 5
4. This is how the story looks with content
5. This is the add Experience layout where user can add their experience that how they felt by reading the story and it will be saved in room database.
6. User can also delete any experience by clicking on it and delete option.

**Functionality Used**

**Used MVVM architecture**

1. UI : The app has a good and interactive UI with splash screen.
2. Constraint Layout : Most of the activities in the app uses a flexible constraint layout, which is easy to handle for different screen sizes.
3. RecyclerView : To present the list of different stories we used the efficient recyclerview.
4. Picsum Api : To recieve different pictures weused Picsum API
5. Live Data , ViewModel & Room Database : It is used to store and update the experience of the user and to store that data
