import { createClient } from "@/utils/supabase/server";

export default async function Category(){
     const supabase =await createClient();
        const { data: categories} = await supabase.from("category").select();
    return (
        <>
            {categories?.map((cat)=>(<p key={cat.id}>{cat.name}</p>))}
        </>
    );
}