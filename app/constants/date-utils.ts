


// Output: April 12, 2025 at 08:57:20 AM GMT (depends on your locale/timezone)

export function formatDate(value:string|Date){
    const date = new Date(value);
   const formattedDate= new Intl.DateTimeFormat("en-US", {
      year: "numeric",
      month: "short", // or '2-digit'
      day: "2-digit",
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
    //   timeZoneName: "short",
    }).format(date);
    return formattedDate;
}