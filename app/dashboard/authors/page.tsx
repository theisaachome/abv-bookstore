import { createClient } from "@/utils/supabase/server";


export default async function AuthorPage(){
         const supabase =await createClient();
            const { data: authors} = await supabase.from("authors").select();
    return (
        <>
        {authors?.map((author)=>(<p className="px-3" key={author.id}>{author.name}</p>))}
        </>
    );
}