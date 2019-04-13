:: AutoSense Database Setup
@ECHO OFF
@SET PGDATABASE=data_dir
@SET PGPORT=7777
@SET PGUSER=senseadmin
@SET PGPASS=%~dp0settings\su_pass.txt
@SET DBLOC=%~dp0data_dir
@SET TLOG=%~dp0Logs\tlogs
@SET PGCTL=%~dp0bin\pg_ctl
@SET INITDB=%~dp0bin\initdb
@SET POSTGRES=%~dp0bin\postgres
@SET SLOG=%~dp0Logs\slogs\serverlog.log
@SET PGLOCALEDIR=%~dp0\share\locale
@SET SNAME="AutoSense_PgServer"

%INITDB% -U %PGUSER% -A password -E UTF8 -D %DBLOC% -X %TLOG% --pwfile=%PGPASS%
@ECHO "Data Cluster  added successfully !!!"

SC QUERY %SNAME% | find "does not exist" > nul
IF  %ERRORLEVEL% EQU 1 (
SC DELETE %SNAME%
GOTO REGISTER
)
ELSE ( GOTO REGISTER )             

:REGISTER

%PGCTL% register -N %SNAME% -U "NT AUTHORITY\NetworkService" -D %DBLOC% -S demand -l %SLOG% -o "-p %PGPORT%" -w
@ECHO %SNAME% registered Successfully as Windows NT Service !!!
Prompt
Prompt
Prompt
@ECHO Setup Completed. Press any key to exit...