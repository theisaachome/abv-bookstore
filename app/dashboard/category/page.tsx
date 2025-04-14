import { categoriesColumns } from "@/components/category/columns";
import { CategoryDataTable } from "@/components/category/data-table";
import { createClient } from "@/utils/supabase/server";

export default async function Category(){
     const supabase =await createClient();
        const { data: categories} = await supabase.from("categories").select();
    return (
      <div className="container mx-auto py-10">
        <CategoryDataTable columns={categoriesColumns} data={categories || []} />
      </div>
    );
}