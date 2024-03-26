using CosmosdbApplicaiton;
using Microsoft.Azure.Cosmos;

string endpoint = "";
string key = "";

string databaseName = "studentsdb";
string containerName = "Orders";

CosmosClient client = new CosmosClient(endpoint, key);
//AccountProperties properties = await client.ReadAccountAsync();

//await CreateDatabase(databaseName);
//await CreateContainer(databaseName, containerName, "/category");

//async Task CreateDatabase(string databaseName)
//{
//    await client.CreateDatabaseIfNotExistsAsync(databaseName);
//    Console.WriteLine("Database created");
//}

//async Task CreateContainer(string databaseName, string containerName, string partitionKey)
//{
//    Database database = client.GetDatabase(databaseName);

//    await database.CreateContainerAsync(containerName, partitionKey);

//    Console.WriteLine("Container created");
//}

await AddItem("O1", "Laptop", 100);
await AddItem("O2", "Mobiles", 200);
await AddItem("O3", "Desktop", 75);
await AddItem("O4", "Laptop", 25);

async Task AddItem(string orderId, string category, int quantity)
{
    Database database = client.GetDatabase(databaseName);
    Container container = database.GetContainer(containerName);

    Order order = new Order()
    {
        id = Guid.NewGuid().ToString(),
        orderId = orderId,
        category = category,
        quantity = quantity
    };

    ItemResponse<Order> response = await container.CreateItemAsync<Order>(order, new PartitionKey(order.category));

    Console.WriteLine("Added item with Order Id {0}", orderId);
    Console.WriteLine("Request Units consumed {0}", response.RequestCharge);

}
