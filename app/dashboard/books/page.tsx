import { createClient } from "@/utils/supabase/server";
import {  columns } from "@/components/books/columns";
import { DataTable } from "@/components/books/data-table";
import { Suspense } from "react";

export default async function Page() {
      const supabase =await createClient();
      const { data: books} = await supabase.from("books").select();

  return (
    <div className="container mx-auto py-10">
      <Suspense>
        <DataTable columns={columns} data={books || []} />
      </Suspense>
    </div>
  );
}
