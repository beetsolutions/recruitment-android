![Agreena](https://agreena.com/wp-content/uploads/2021/06/agreena-logo.svg)

# Android recruitment test task

### Carbon Certificates app
Create a simple application displaying the list of available Carbon certificates

##### Features
###### "Certificates" view - the list of available Carbon certificates 
- A simple list showing the ID and Owner of each certificate
- Items can be favorited

  ![alt text]([https://github.com/fieldmargin-recruitment/recruitment-android/blob/main/screenshot_example.png] "Example")

###### "Detail" view - detail view for a single certificate
- Shows all available data for a given certificate

##### Technical information
For fetching the certificates use our endpoint:
`https://demo.api.agreena.com/api/public/carbon_registry/v1/certificates?includeMeta=true&page=1&limit=10`

(`API-ACCESS-TOKEN` header with value `Commoditrader-React-FE-Farmer` is needed to authenticate the endpoint)

Keep in mind that there could be more than 10 certificates.


##### Requirements
- Application should be written in Kotlin
- Application should have a simple UX/UI
- Application code quality is very important
- Spend no more than 4 hours on the assignment

If you have any other ideas how to make this app working and looking better - feel free to implement it!

### Good luck!
