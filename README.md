# moneyxfer-mobi
Description: Mobile app using Android Studio to transfer money

## Student details
Soh Jia Yu, Eunice (p7265742)

## Description
Want to transfer money to your friend? Hesitate no further, use moneyXfer. Sign up now with your email and login now. As an extra feature, the app also provides an in-built reminder list so that you won’t forget to transfer money to your loved ones!

## Features summary

Assignment 1 features (+ enhancements during assignment 2 in **bold**):
- Splash page and login feature
  - Login with email and password **with Google Firebase authentication**
  - Login attempt counter
  - **Store email in SharedPreferences**
  - **Logout feature with Action Bar**
- Transfer money feature
  - Transfer money (validated fields) **with Google Firebase realtime database**
- Reminder list feature
  - Add and remove reminders that are persisted locally (json file)

Assignment 2 features:
- Main page with menu, a random quote from https://quote-garden.herokuapp.com/quotes/random and a random dog image from https://shibe.online/ (using external content providers and downloading images)
- View transactions feature (getting realtime data from Firebase noSQL db)
  - View money sent
  - View money received

Altogether = 5 main features

## How to use

- Login with dummy account
    Email: foo@bar.com
    Password: hello123

## noSQL structure
- Transactions
  - transactionId PK
  - fromID
  - toID
  - amount
  - transactionDate

(old structure when using Firebase database)
- Users
  - uid PK
  - email (unique)
  - password