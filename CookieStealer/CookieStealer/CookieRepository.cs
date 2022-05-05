using CookieStealer.Controllers;

namespace CookieStealer
{
    public static class CookieRepository
    {
        public static IList<StolenCookie> Cookies { get; private set; } = new List<StolenCookie>();
    }
}
