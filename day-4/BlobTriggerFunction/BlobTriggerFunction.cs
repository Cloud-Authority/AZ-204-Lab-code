using System;
using System.IO;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Host;
using Microsoft.Extensions.Logging;

namespace BlobTriggerFunction
{
    public class BlobTriggerFunction
    {
        [FunctionName("BlobTriggerFunction2")]
        public void Run([BlobTrigger("data/{name}", Connection = "AzureWebJobsStorage")]Stream myBlob,
            string name, ILogger log)
        {
            log.LogInformation($"Processed blob\n Name:{name} \n Size: {myBlob.Length} Bytes");
        }
    }
}
