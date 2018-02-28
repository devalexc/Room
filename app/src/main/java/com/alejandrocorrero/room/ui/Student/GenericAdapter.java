package com.alejandrocorrero.room.ui.Student;


import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

// TODO Comentar uso de long click y empty view
//Para usar este adaptador crear clase Pojo que extienda de  base adapter y asignar bindings, añadir bindings al xml item del adaptador
// y asignar cada campo con su binding correspondiente(como cualquier DataBinding) crear adaptador añadiendo los datos del modelBR y
// y el layout de los items y llamar al metodo setlist del adaptador creado pasandole la lista de datos que pintará
//
// *** Para asignar Click de item al reycle añadir   app:onItemClick="@{(view, item, position) -> presenter.onItemClick(view, item, position)}"
// en el RecycleView del xml , en ese xml crear variable binding apuntando a la clase donde se ejecutará el on clic
// en este caso MainActivity y la variable se llama presenter, añadir al binding de dicha clase el set presenter(this) y añadir el
// metodo onclick onItemClick(View view,Object item,int position) en dicha clase

// Adaptador generico , generizado sobre la Clase Pojo extiende del viewHolder que se encuentra en esta clase.
public  class GenericAdapter<T> extends RecyclerView.Adapter<GenericAdapter.ViewHolder> {
    // Variable del model BR se obtiene con BR.ClasePojo que corresponde al nombre elegido en el binding (por ejemplo BR.item)
    private final int modelBRId;
    // Lista generica de los objetos de recycleView
    private List<T> listClasePojo = new ArrayList<>();
    // Layout correspondiente al layout de los items (por ejemplo R.layout.activity_main_item)
    private int layoutIdBinding;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private View emptyView;

    // Constructor que recibe el BR item del Binding y el id del layout donde se efectua el binding
    GenericAdapter(int modelBR, int layoutAdapterItem) {
        this.modelBRId = modelBR;
        this.layoutIdBinding = layoutAdapterItem;
    }

    //  Creacion del viewHolder creando un binding generico de la clase padre
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int idLayout) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), idLayout, parent, false);
        View itemView = binding.getRoot();
        ViewHolder viewHolder = new ViewHolder(binding, modelBRId);
        // Corresponde al click del recycle , este llama a la interfaz y genera el click
        if (onItemClickListener != null) {

            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v, getItem(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition()));
        }
        if (onItemLongClickListener != null) {
            itemView.setOnLongClickListener(view -> {
                onItemLongClickListener.onItemLongClick(view, getItem(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
                return true;
            });
        }
        return viewHolder;
    }

    private Object getItem(int adapterPosition) {
        return listClasePojo.get(adapterPosition);
    }

    // Asignacion de cada final normal de un RecycleView
    @Override
    public void onBindViewHolder(GenericAdapter.ViewHolder holder, int position) {
        holder.bind(listClasePojo.get(position));
    }

    // Metodo que asigna la lista al adaptador y notifica que se han cambiado los objetos
    void setList(List<T> list) {
        this.listClasePojo = list;
        notifyDataSetChanged();
        checkEmpty(list.size());
    }

    // Deja ver o oculta el emtyView
    private void checkEmpty(int size) {
        if(emptyView!=null){
            emptyView.setVisibility(size>0? View.GONE:View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listClasePojo.size();
    }

    

    // Clase ViewHolder n
    class ViewHolder extends RecyclerView.ViewHolder {

        // Clase padre de los Bindinds
        private final ViewDataBinding binding;
        // Variable del model BR
        private final int modelBRId;

        ViewHolder(ViewDataBinding binding, int modelBRId) {
            super(binding.getRoot());
            this.binding = binding;
            this.modelBRId = modelBRId;
        }

        // Bind generico utilizando setVariable generico y la clase Object generica de todos los objetos
        void bind(Object item) {
            binding.setVariable(modelBRId, item);
            binding.executePendingBindings();
        }

    }

    // Metodo que asigna el idLayout en el onCreateViewHolder , en este caso este layout se pasa en el constructor
    @Override
    public int getItemViewType(int position) {
        return layoutIdBinding;
    }


    // Metodo binding del click
    @BindingAdapter("app:onItemClick")
    public static void onItemClick(RecyclerView recyclerView, final OnItemClickListener action) {
        if (recyclerView != null && recyclerView.getAdapter() instanceof GenericAdapter) {
            GenericAdapter adapter = (GenericAdapter) recyclerView.getAdapter();
            adapter.setOnItemClickListener(action);
        }
    }

    // Metodo binding del long click
    @BindingAdapter("app:onItemLongClick")
    public static void onItemLongClick(RecyclerView recyclerView, final OnItemLongClickListener action) {
        if (recyclerView != null && recyclerView.getAdapter() instanceof GenericAdapter) {
            GenericAdapter adapter = (GenericAdapter) recyclerView.getAdapter();
            adapter.setOnItemLongClickListener(action);
        }
    }

    // Interfazpara la comunicacion estatica entre el click del recycle
    public interface OnItemClickListener {
        void onItemClick(View view, Object item, int position);
    }
    // Interfaz para la comunicacion estatica entre long click del recycle
    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, Object item, int position);
    }

    // Comunicacion entre estatico y clase
    private void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    // Comunicacion entre estatico y clase
    private void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }

    //Asigna la propiedad binding  empty view en el recycleview
    @BindingAdapter("app:emptyView")
    public static void addEmptyView(RecyclerView recyclerView, View emptyView) {
        if (recyclerView != null && emptyView != null
                && recyclerView.getAdapter() instanceof GenericAdapter) {
            GenericAdapter adapter = (GenericAdapter) recyclerView.getAdapter();
            adapter.setEmptyView(emptyView);
        }
    }
    // Asigna el empty view
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkEmpty(getItemCount());
    }

}