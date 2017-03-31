# CustomContactList
This is a simple contact list. We’ve three major sections here. 

The header
The header is fixed. It won’t scroll with the list. 
The header contains your name. Please check of long names. The maximum line for the TextView is 2.
Header contains your number. 
It contains a tick at the right side of your name. The tick will be provided with the project.  Please don’t change the size of the tick images.
Place the tick just right to the name. If the name acquires 2 line, place the tick vertically centered with the name and yes, just right to the name (NOT Right-Aligned). 
I didn’t provide the profile image. You will use some of your own. The Profile image size is 100*100 dp (customizable) with rounded corners like the one you can see in the image above. 

The Contact list
Get all contacts from your phone to a Sqlite Database. Save only the name and phone numbers for now. 
Show the first ten from your Sqlite database in the list. (Use RecyclerView to show the list). 
Then add a Load More button in at the end of the list. The Load More button will be added as a footer of the list. 

The Load More button
As you see the button is added as a footer so it’ll scroll with the list. By clicking the Load More button you’ll fetch the next 10 numbers from your Sqlite Database and add the contacts with the existing contact list. 
When all the data from your Sqlite database is fetched already, show “No more results to show” instead of “Load More” . 
