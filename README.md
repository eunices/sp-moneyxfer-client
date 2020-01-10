# moneyxfer-mobi
Description: Mobile app using Android Studio to transfer money

## Student details
Soh Jia Yu, Eunice (p7265742)

## Description
Want to transfer money to your friend? Hesitate no further, use moneyXfer. Sign up now with your email and login now. As an extra feature, the app also provides an in-built reminder list so that you won’t forget to transfer money to your loved ones!

## Features summary

Assignment 1 features:
- Splash page and login feature
  - Login with email and password
  - Login attempt counter
  - Store email in localstore?
- Transfer money feature
  - Transfer money (validated fields)
- Reminder list feature
  - Add and remove reminders that are persisted locally

Assignment 2 features:
- Main page with menu and weather information
  - from https://www.metaweather.com/api/
- View transactions feature
  - View money sent
  - View money received
** additional ideas: change password

## noSQL structure
- Users
  - uid PK
  - email (unique)
  - password
- Transactions
  - transactionId PK
  - fromID
  - toID
  - amount
  - transactionDate

Altogether = 5 main features