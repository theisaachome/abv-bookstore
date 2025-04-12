import { createClient } from "@/utils/supabase/server";


export default async function Orders() {
   const supabase =await createClient();
     const { data: notes } = await supabase.from("notes").select();
  return (
    <div className="container">
      <h1>Orders</h1>
      <p>Coming soon...</p>
      <pre>{JSON.stringify(notes, null, 2)}</pre>
    </div>
  );
}