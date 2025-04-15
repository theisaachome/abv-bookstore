import { createClient } from "@/utils/supabase/server";
import { columns } from "@/components/authors/columns";
import { AuthorsDataTable } from "../datatable";

export default async function AuthorsTable(){
      const supabase =await createClient();
      const { data: authors } = await supabase.from("authors").select();
  return (
    <div className="container mx-auto py-10">
      <AuthorsDataTable columns={columns} data={authors || []} />
    </div>
  );
}
