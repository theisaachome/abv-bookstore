import { createClient } from "@/utils/supabase/server";

export default async function Books() {
    const supabase =await createClient();
    const { data: books} = await supabase.from("books").select();
    return (
        <>
            <div className="container">
               {books?.map((book)=>(<p key={book.id}>{book.title}</p>))}
            </div>
        </>
    );
}