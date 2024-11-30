# The Principles of Universal Design

## 1: Equitable Use
- Feature that could be implemented in the future: provide more options for how inputs are provided to the program from the user, and how outputs are conveyed from the program (more below in flexibility of use and perceptible information)
- Feature that could be implemented in the future: adjustable UI elements, allowing users to move interaction points to places that are most intuitive and comfortable for them
- The application does not violate the privacy, security, or safety of any type of user, and in fact does not collect, store or distribute any user data

## 2: Flexibility in Use
- Feature that could be implemented in the future: integration with input methods other than mouse; ex. voice control; currently the entire application is interacted with only by mouse clicks
- Feature that could be implemented in the future: language support, currently the application supports only English
- Feature that could be implemented in the future: an option to have more detailed information presented during a game of Blackjack, ie. calculate the player's and dealer's hand values (whereas other players may prefer to calculate it themselves for fun!)

## 3: Simple and Intuitive Use
- All parts of our user interface are very simple and intuitive, and have no elements beyond interactable buttons and short text labels
-  There is no unnecessary complexity like decorative UI elements or unique layouts beyond simple vertical and horizontal layouts
- All interactable buttons are labelled clearly and succinctly, and labels state explicitly what they represent and are sometimes colour coded, leaving no ambiguity of their function

## 4: Perceptible Information
- All presented text is succinct and simple, making it very legible and descriptive of essential information
- Feature that could be implemented in the future: integration with screen readers or built-in screen reader for blind users to be able to play
- Feature that could be implemented in the future: responsive design with adjustable font-sizes and adjustable window size for vision-impaired users

## 5: Tolerance for Error
- Within a game of Blackjack, the application automatically locks the user's hand in if they get 21, averting the possibility of the user miscounting and hitting again!
- The application offers easy back-and-forth navigation; hitting buttons accidentally can usually be simply undone by clicking a "back" button
- Feature that could be implemented in the future: have confirmation pop-ups for interaction points that have significant effects; ie. exit button prompts "are you sure you want to exit"

## 6: Low Physical Effort
- The entire application can be interacted with by only mouse clicks, which are fairly low physical demand
- The entire application has no points which require sustained physical effort, rapid repetitive actions, or quick actions
- Feature that could be implemented in the future: integration with alternative input methods, ie. voice control, which is even lower physical effort

## 7: Size and Space for Approach and Use
As previously mentioned, features that could be implemented in the future:
- Responsive design with adjustable font-sizes and adjustable window size for vision-impaired users
- Adjustable UI elements, (ex. button placement, background colour) to make the design appealing and easy to use to various tastes
- Ensure that interactive elements are placed and sized such that they are easy to click, ex. in case of users on touchscreens, laptop trackpads, etc.

# Who you would market your program towards?
If we were sell or license our program to customers, the category with the most potential would likely be the mobile gaming market, and more specifically the Android market. This is because the Android OS is fundamentally built around running Java applications (granted, many Android applications today are built using Kotlin, and are built specifically for Android using Android Studio, whereas Intellij is more for traditional desktop apps). This is mostly due to access; for example we would not be able to successfully distribute our application as an online in-browser game, because most internet browsers today very specifically do not support executing any Java in-browser (for security reasons)! We would also be unlikely to successfully penetrate the desktop/laptop installed application market, as most common OSes already come with prepackaged card game applications (most commonly Solitaire, but other card games are common as well).

# Less likely to be used by certain demographics?
Due to the accessibility limitations identified among in The Principles of Universal Design section, there are some demographics less likely to use our application. Two examples are vision-impaired and blind people; this is because our application currently does not implement responsive design and do not integrate with any screen readers. Without adjustable font-sizes and window sizes, vision-impaired users might find it difficult to use the application, and without screen-reading, blind users are entirely prevented from using the application. Another example is people who cannot use tactile inputs like mouse clicks or screen taps. This is because our application does not currently offer any alternative input methods like voice control, so these users would be unable to interact with it.