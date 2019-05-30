set classpath=
start "D:\Java\bin\rmiregistry 5002"
cd C:\PAI_Lab4_6377\Serwer_RMI_Sklep_6SE\dist
"D:\Java\bin\java" -cp -Djava.rmi.server.codebase=file:C:\PAI_Lab4_6377\Serwer_RMI_Sklep_6SE\dist\Serwer_RMI_Sklep_6SE.jar -Djava.security.policy=C:\PAI_Lab4_6377\Serwer_RMI_Sklep_6SE\policy_server1 -jar Serwer_RMI_Sklep_6SE.jar
pause