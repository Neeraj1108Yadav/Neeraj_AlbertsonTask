# Random User Finder Using Jetpack Compose :clipboard:

This project displays a bulk list of random users fetched via a network call. Users can scroll through the list, search for a specific user,
and view detailed information about a user by tapping on their card.

## Features

- **Bulk User Fetching:** Fetch and display random user data efficiently.
- **User Details:** Tap on a user card to view detailed information about the selected user.
- **Smooth Navigation:** Seamlessly navigate between the list and detail screens.
- **Unit Testing:** Some unit test on network and pagination.

## Tech Stack

- **Kotlin:** Prgramming Language
- **Architecture Pattern:** MVVM
- **Jetpack Compose**
- **Jetpack Components:**
  - **ViewModel:** To manage UI-related data in a lifecycle-conscious way.
- **Networking:** Retrofit with Gson for API communication.
- **Unit Testing:**
  - **JUnit:** For unit tests.
  - **Mockito:** For mocking dependencies.
  - **MockWebServer:** To mock network responses for Retrofit testing.
  - **Compose UI Test**
  - **Turbine:** To test flows.

## Screenshots Light Theme

![loader](https://github.com/user-attachments/assets/0394172e-e739-4074-b694-544bab05129a) ![list_light](https://github.com/user-attachments/assets/352d2b59-2277-4e99-977d-960efc04be15) ![dialog_error](https://github.com/user-attachments/assets/b0135e29-80e0-45c2-80c2-445a38b5cdd7) ![dialog_input](https://github.com/user-attachments/assets/ef1298d3-bd00-437f-9f9f-be4e8ffd6e67) ![info_light](https://github.com/user-attachments/assets/b140f467-3bc7-4eed-935a-a67193812374)
 

## Screenshots Dark Theme

![list_dark](https://github.com/user-attachments/assets/a6d79442-5325-48d8-a15b-060cd5f7b878) ![info_dark](https://github.com/user-attachments/assets/d4ad3cd9-04f5-4322-a705-de68a510a23d)



## Unit Test

- **Repository Testing:** Validates data retrieval and transformation.
- **Retrofit Service Testing:** Uses MockWebServer to test API calls.
- **Comopse UI Testing:** Test of Compose components
- **ViewModel Test:** With help of Turbine unit test perfomed for return result.

## Contributions

Contributions are welcome! If you'd like to make improvements or fix issues, feel free to open a pull request or report an issue.
