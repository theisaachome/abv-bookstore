"use client";

import { formatDate } from "@/app/constants/date-utils";
import { ColumnDef } from "@tanstack/react-table";
import { Button } from "../ui/button";
import { GripVerticalIcon } from "lucide-react";
import {
  SortableContext,
  arrayMove,
  useSortable,
  verticalListSortingStrategy,
} from "@dnd-kit/sortable";
import { Checkbox } from "../ui/checkbox";

// Create a separate component for the drag handle
function DragHandle({ id }: { id: number }) {
  const { attributes, listeners } = useSortable({
    id,
  })

  return (
    <Button
      {...attributes}
      {...listeners}
      variant="ghost"
      size="icon"
      className="size-7 text-muted-foreground hover:bg-transparent"
    >
      <GripVerticalIcon className="size-3 text-muted-foreground" />
      <span className="sr-only">Drag to reorder</span>
    </Button>
  )
}

export type Book = {
  id: number;
  title: string;
  isbn?: string;
  publisher?: string;
  author?: string;
  price: number;
  stock_quantity: number;
  category_id?: number;
  author_id?: number;
  created_at: string; // ISO timestamp format
  updated_at: string; // ISO timestamp format
};


export const columns: ColumnDef<Book>[] = [
  {
    id: "drag",
    header: () => null,
    cell: ({ row }) => <DragHandle id={row.original.id} />,
  },
  {
    id: "select",
    header: ({ table }) => (
      <div className="flex items-center justify-center">
        <Checkbox
          checked={
            table.getIsAllPageRowsSelected() ||
            (table.getIsSomePageRowsSelected() && "indeterminate")
          }
          onCheckedChange={(value) => table.toggleAllPageRowsSelected(!!value)}
          aria-label="Select all"
        />
      </div>
    ),
    cell: ({ row }) => (
      <div className="flex items-center justify-center">
        <Checkbox
          checked={row.getIsSelected()}
          onCheckedChange={(value) => row.toggleSelected(!!value)}
          aria-label="Select row"
        />
      </div>
    ),
    enableSorting: false,
    enableHiding: false,
  },
  {
    accessorKey: "title",
    header: "Book Title",
  },
  {
    accessorKey: "author",
    header: "Author",
  },

  {
    accessorKey: "price",
    header: () => <div className="text-right">Price</div>,
    cell: ({ row }) => {
      const amount = parseFloat(row.getValue("price"));

      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "MMK",
        currencyDisplay: "code", // gives you 'USD' instead of '$'
      }).format(amount);

      // Rearrange "USD 100.00" to "100.00 USD"
      const rearranged = formatted.replace(/^MMK\s?/, "") + " MMK";

      return <div className="text-right font-medium">{rearranged}</div>;
    },
  },
  {
    accessorKey: "stock_quantity",
    header: () => <div className="text-right">Stock Unit</div>,
    cell: ({ row }) => {
      const stock_quantity = parseFloat(row.getValue("stock_quantity"));
      return <div className="text-right font-medium">{stock_quantity}</div>;
    },
  },
  {
    accessorKey: "isbn",
    header: "isbn",
  },
  {
    accessorKey: "created_at",
    header: "Created_at",
    cell: ({ row }) => {
      const formattedDate = formatDate(row.getValue("created_at"));
      return <>{formattedDate}</>;
    },
  },

  {
    accessorKey: "updated_at",
    header: "Updated_at",
    cell: ({ row }) => {
      const formattedDate = formatDate(row.getValue("updated_at"));
      return <>{formattedDate}</>;
    },
  },
];
