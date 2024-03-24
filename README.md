# Numeric bank API
This repository contains an API for a Numeric Bank 
# MCD/MLD
You can view the `MCD` and `MLD`here: [MCD&MLD](./src/main/resources/MCD&MLD)
You can already visualize what look like the **MCD**: 
![MCD](./src/main/resources/MCD&MLD/mcd-bank.png)
# DB migration
For this project, we use two script for the database migration. So we need to follow these instructions:
Open a new terminal and go to the directory **db/migration**:
```sh
$ cd src/main/resources/db/migration
```
Well, the next step is running the script:
- for Windows OS, run the command:
```sh
$ db_migration.bat
```
- for Linux OS or macOS, you have to give execution permissions to your script before running it:
 - give the permission:
   ```sh
     $ sudo chmod +x db_migration.sh
    ```
 - run the script
    ```sh
    $ db_migration.sh
    ```
> We use directly the **postgres** as username in the database  
# Openapi specification
You can see the **api specification** in the following link: [API Specification](https://petstore.swagger.io/?url=https://raw.githubusercontent.com/prog4-final-exam/numeric-bank-backend/main/docs/api.yaml)