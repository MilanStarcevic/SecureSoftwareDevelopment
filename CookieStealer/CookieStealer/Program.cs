var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();

var AllowAll = "_allowAll";
builder.Services.AddCors(options =>
{
    options.AddDefaultPolicy(policy =>
                      {
                          policy.AllowAnyHeader();
                          policy.AllowAnyMethod();
                          policy.AllowAnyOrigin();
                      });
});

var app = builder.Build();

// Configure the HTTP request pipeline.

app.UseCors();

app.UseAuthorization();

app.MapControllers();

app.Run();
