import AuthorsTable from "@/components/authors/table";
import { SkeletonTableBasic } from "@/components/skeletons";
import { createClient } from "@/utils/supabase/server";
import { Suspense } from "react";


export default async function AuthorPage(){
    return (
        <div className="w-full">
            <Suspense fallback={<SkeletonTableBasic/>}>
                <AuthorsTable />
            </Suspense>
        </div>
    );
}