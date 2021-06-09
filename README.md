 ____    ____  ____      __   ___   ___      ___       _____   __   ____  ____   ____     ___  ____  
|    \  /    ||    \    /  ] /   \ |   \    /  _]     / ___/  /  ] /    ||    \ |    \   /  _]|    \ 
|  o  )|  o  ||  D  )  /  / |     ||    \  /  [_     (   \_  /  / |  o  ||  _  ||  _  | /  [_ |  D  )
|     ||     ||    /  /  /  |  O  ||  D  ||    _]     \__  |/  /  |     ||  |  ||  |  ||    _]|    / 
|  O  ||  _  ||    \ /   \_ |     ||     ||   [_      /  \ /   \_ |  _  ||  |  ||  |  ||   [_ |    \ 
|     ||  |  ||  .  \\     ||     ||     ||     |     \    \     ||  |  ||  |  ||  |  ||     ||  .  \
|_____||__|__||__|\_| \____| \___/ |_____||_____|      \___|\____||__|__||__|__||__|__||_____||__|\_|
                                                                                                     


The program is designed to accept text input from a Barcode Scanner and will validate it against data stored in the Database.
This program displays prices for a given barcode existing in the database and also has the ability to insert/update inventory data.

~~To use the price check application, you must run the executable jar  with "SCAN" as an argument.
To use the inventory application, you must run the executable jar with "ADD" as an argument.~~

Required Environment Variables: DB_USERNAME, DB_PASSWORD
~~Required Argument: SCAN or ADD~~

Assumptions: Program uses MySQL as the datasource.