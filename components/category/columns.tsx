"use client"

import * as React from "react"
import {
  SortableContext,
  arrayMove,
  useSortable,
  verticalListSortingStrategy,
} from "@dnd-kit/sortable"
import {
  ColumnDef
} from "@tanstack/react-table"
import {
  GripVerticalIcon,
} from "lucide-react"
import { Button } from "@/components/ui/button"
import { Checkbox } from "@/components/ui/checkbox"
import Category from "@/app/dashboard/category/page"


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
export type Category = {
  id: number;
  name: string;
  description?: string;
  status: boolean
};


export const categoriesColumns: ColumnDef<Category>[] = [
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
    accessorKey: "name",
    header: "Name",
  },
  {
    accessorKey: "description",
    header: "Description "
  },
  {
    accessorKey: "isActive",
    header: "Status",
  }
]

// function DraggableRow({ row }: { row: Row<z.infer<Category>> }) {
//   const { transform, transition, setNodeRef, isDragging } = useSortable({
//     id: row.id,
//   });

//   return (
//     <TableRow
//       data-state={row.getIsSelected() && "selected"}
//       data-dragging={isDragging}
//       ref={setNodeRef}
//       className="relative z-0 data-[dragging=true]:z-10 data-[dragging=true]:opacity-80"
//       style={{
//         transform: CSS.Transform.toString(transform),
//         transition: transition,
//       }}
//     >
//       {row.getVisibleCells().map((cell) => (
//         <TableCell key={cell.id}>
//           {flexRender(cell.column.columnDef.cell, cell.getContext())}
//         </TableCell>
//       ))}
//     </TableRow>
//   );
// }
