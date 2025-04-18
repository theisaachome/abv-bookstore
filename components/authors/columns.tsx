"use client";

import { ColumnDef } from "@tanstack/react-table";

// This type is used to define the shape of our data.
// You can use a Zod schema here if you want.
export type Author = {
  id: number;
  name: number;
  bio: number;
};

export const columns: ColumnDef<Author>[] = [
  {
    accessorKey: "name",
    header: "Name",
  },
  {
    accessorKey: "bio",
    header: "bio",
  },
];
