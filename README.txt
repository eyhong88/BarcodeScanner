
                                                    ,----..                                                                                 ,--.         ,--.
    ,---,.    ,---,       ,-.----.     ,----..     /   /   \      ,---,        ,---,.          .--.--.     ,----..     ,---,              ,--.'|       ,--.'|    ,---,.,-.----.
  ,'  .'  \  '  .' \      \    /  \   /   /   \   /   .     :   .'  .' `\    ,'  .' |         /  /    '.  /   /   \   '  .' \         ,--,:  : |   ,--,:  : |  ,'  .' |\    /  \
,---.' .' | /  ;    '.    ;   :    \ |   :     : .   /   ;.  \,---.'     \ ,---.'   |        |  :  /`. / |   :     : /  ;    '.    ,`--.'`|  ' :,`--.'`|  ' :,---.'   |;   :    \
|   |  |: |:  :       \   |   | .\ : .   |  ;. /.   ;   /  ` ;|   |  .`\  ||   |   .'        ;  |  |--`  .   |  ;. /:  :       \   |   :  :  | ||   :  :  | ||   |   .'|   | .\ :
:   :  :  /:  |   /\   \  .   : |: | .   ; /--` ;   |  ; \ ; |:   : |  '  |:   :  |-,        |  :  ;_    .   ; /--` :  |   /\   \  :   |   \ | ::   |   \ | ::   :  |-,.   : |: |
:   |    ; |  :  ' ;.   : |   |  \ : ;   | ;    |   :  | ; | '|   ' '  ;  ::   |  ;/|         \  \    `. ;   | ;    |  :  ' ;.   : |   : '  '; ||   : '  '; |:   |  ;/||   |  \ :
|   :     \|  |  ;/  \   \|   : .  / |   : |    .   |  ' ' ' :'   | ;  .  ||   :   .'          `----.   \|   : |    |  |  ;/  \   \'   ' ;.    ;'   ' ;.    ;|   :   .'|   : .  /
|   |   . |'  :  | \  \ ,';   | |  \ .   | '___ '   ;  \; /  ||   | :  |  '|   |  |-,          __ \  \  |.   | '___ '  :  | \  \ ,'|   | | \   ||   | | \   ||   |  |-,;   | |  \
'   :  '; ||  |  '  '--'  |   | ;\  \'   ; : .'| \   \  ',  / '   : | /  ; '   :  ;/|         /  /`--'  /'   ; : .'||  |  '  '--'  '   : |  ; .''   : |  ; .''   :  ;/||   | ;\  \
|   |  | ; |  :  :        :   ' | \.''   | '/  :  ;   :    /  |   | '` ,/  |   |    \        '--'.     / '   | '/  :|  :  :        |   | '`--'  |   | '`--'  |   |    \:   ' | \.'
|   :   /  |  | ,'        :   : :-'  |   :    /    \   \ .'   ;   :  .'    |   :   .'          `--'---'  |   :    / |  | ,'        '   : |      '   : |      |   :   .':   : :-'
|   | ,'   `--''          |   |.'     \   \ .'      `---`     |   ,.'      |   | ,'                       \   \ .'  `--''          ;   |.'      ;   |.'      |   | ,'  |   |.'
`----'                    `---'        `---`                  '---'        `----'                          `---`                   '---'        '---'        `----'    `---'


The program is designed to accept text input from a Barcode Scanner and will validate it against data stored in the Database.
The intent of this program is to display prices for a given barcode existing in the database.

There will be a follow-up program that will be used as inventory management.
This will allow for a user to do CRUD (CREATE/READ/UPDATE/DELETE) operations against the inventory data stored in the Database.
This follow-up program can be used to pre-seed the database, if manually inserting records is not possible.

Required Environment Variables: DB_USERNAME, DB_PASSWORD

Assumptions: Program uses MySQL as the datasource.