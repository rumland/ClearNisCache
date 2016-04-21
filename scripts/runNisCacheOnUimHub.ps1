$ip = "need to populate"
copy C:\Users\umlro01\IdeaProjects\ClearNisCache\out\production\ClearNisCache\ca\java\*.class \\$ip\c$\temp\ca\java\.
$Username = "Administrator"
$Password = "t3sti9" | ConvertTo-SecureString -asPlainText -Force
$Credential = new-object -typename System.Management.Automation.PSCredential -argumentlist $Username, $Password
Invoke-Command -ComputerName $ip -ScriptBlock { cd 'C:\temp\'; java ca.java.Main; } -Credential $Credential