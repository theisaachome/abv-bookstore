import { createClient } from "@/utils/supabase/server";

// export default async function Books() {
//     const supabase =await createClient();
//     const { data: books} = await supabase.from("books").select();
//     return (
//         <>
//             <div className="container">
//                {books?.map((book)=>(<p key={book.id}>{book.title}</p>))}
//             </div>
//         </>
//     );
// }

import {  columns } from "@/components/books/columns";
import { DataTable } from "@/components/books/data-table";


export default async function DemoPage() {
      const supabase =await createClient();
      const { data: books} = await supabase.from("books").select();

  return (
    <div className="container mx-auto py-10">
      <DataTable columns={columns} data={books || []} />
    </div>
  );
}
