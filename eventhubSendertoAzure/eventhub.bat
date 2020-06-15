cd D:\AzureProject\eventhub\src\main\resources
az login
az account list
az account set -s 11111111-1111-1111-1111-111111111111
az ad sp create-for-rbac --sdk-auth > my.azureauth